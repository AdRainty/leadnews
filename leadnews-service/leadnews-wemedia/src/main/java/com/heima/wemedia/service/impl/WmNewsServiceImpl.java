package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmContent;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.enums.WemediaConstants;
import com.heima.model.wemedia.enums.WmNewsStatus;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class WmNewsServiceImpl  extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    private final WmNewsMaterialMapper wmNewsMaterialMapper;

    private final WmMaterialMapper wmMaterialMapper;

    @Override
    public ResponseResult submitNews(@Valid WmNewsDto wmNewsDto) {
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(wmNewsDto, wmNews);
        if (CollectionUtils.isNotEmpty(wmNewsDto.getImages())) {
            String imageStr = StringUtils.join(wmNewsDto.getImages(), ",");
            wmNews.setImages(imageStr);
        }
        if (WemediaConstants.WM_NEWS_TYPE_AUTO.equals(wmNews.getType())) {
            wmNews.setType(null);
        }
        saveOrUpdateWmNews(wmNews);

        if (WmNewsStatus.NORMAL.getCode().equals(wmNewsDto.getStatus())) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        List<String> materials =  extractUrlInfo(wmNewsDto.getContent());
        saveRelativeInfoForContent(materials, wmNews.getId());
        saveRelativeInfoForCover(wmNewsDto, wmNews, materials);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 如果当前封面类型为自动，则设置封面类型的数据
     * 如果内容图片大于等于1 小于3 单图， type = 1
     * 如果内容图片大于3 单图， type = 3
     * 如果内容没有图片 无图 type 0
     * <p>
     * 保存封面图片与素材的关系
     * @param wmNewsDto
     * @param wmNews
     * @param materials
     */
    private void saveRelativeInfoForCover(WmNewsDto wmNewsDto, WmNews wmNews, List<String> materials) {
        List<String> images = wmNewsDto.getImages();
        if (WemediaConstants.WM_NEWS_TYPE_AUTO.equals(wmNewsDto.getType())) {
            if (materials.size() >= 3) {
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = materials.stream().limit(3).collect(Collectors.toList());
            } else if (CollectionUtils.isNotEmpty(materials)) {
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materials.stream().limit(1).collect(Collectors.toList());
            } else {
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }

            if (CollectionUtils.isNotEmpty(images)) {
                wmNews.setImages(StringUtils.join(images, ","));
            }
            updateById(wmNews);
        }

        if (CollectionUtils.isNotEmpty(images)) {
            saveRelativeInfo(images, wmNews.getId(), WemediaConstants.WM_COVER_REFERENCE);
        }

    }

    /**
     * 保存文章图片于素材的关系
     * @param materials
     * @param newsId
     */
    private void saveRelativeInfoForContent(List<String> materials, Integer newsId) {
        saveRelativeInfo(materials, newsId, WemediaConstants.WM_CONTENT_REFERENCE);
    }

    /**
     * 保存文章图片于素材的关系到数据库中
     * @param materials
     * @param newsId
     * @param type
     */
    private void saveRelativeInfo(List<String> materials, Integer newsId, Integer type) {
        if (CollectionUtils.isEmpty(materials)) {
            return;
        }
        List<Integer> collect = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, materials))
                .stream().map(WmMaterial::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            throw new CustomException(AppHttpCodeEnum.MATERIAL_REFERENCE_FAIL);
        }
        if (collect.size() != materials.size()) {
            throw new CustomException(AppHttpCodeEnum.MATERIAL_REFERENCE_FAIL);
        }
        wmNewsMaterialMapper.saveRelations(collect, newsId, type);
    }

    /**
     * 提取文章内容中的图片信息
     * @param content
     * @return
     */
    private List<String> extractUrlInfo(String content) {
        List<WmContent> wmContents = JSON.parseArray(content, WmContent.class);
        return wmContents.stream()
                .filter(item -> WemediaConstants.WM_NEWS_TYPE_IMAGE.equals(item.getType()))
                .map(WmContent::getValue)
                .collect(Collectors.toList());
    }

    /**
     * 保存或修改文章
     * @param wmNews
     */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable(1);

        if (wmNews.getId() == null) {
            save(wmNews);
        } else {
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId, wmNews.getId()));
            this.updateById(wmNews);
        }
    }

    @Override
    public ResponseResult findList(WmNewsPageReqDto wmNewsPageReqDto) {
        wmNewsPageReqDto.checkParam();

        LambdaQueryWrapper<WmNews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(wmNewsPageReqDto.getStatus() != null, WmNews::getStatus, wmNewsPageReqDto.getStatus())
                .eq(wmNewsPageReqDto.getChannelId() != null, WmNews::getChannelId, wmNewsPageReqDto.getChannelId())
                .between(wmNewsPageReqDto.getBeginPubDate() != null && wmNewsPageReqDto.getEndPubDate() != null,
                        WmNews::getPublishTime, wmNewsPageReqDto.getBeginPubDate(), wmNewsPageReqDto.getEndPubDate())
                .like(StringUtils.isNotBlank(wmNewsPageReqDto.getKeyword()), WmNews::getTitle, wmNewsPageReqDto.getKeyword())
                .eq(WmNews::getUserId, WmThreadLocalUtil.getUser().getId())
                .orderByDesc(WmNews::getPublishTime);

        IPage<WmNews> page = page(new Page<>(wmNewsPageReqDto.getPage(), wmNewsPageReqDto.getSize()), wrapper);
        return new PageResponseResult(page);
    }
}
