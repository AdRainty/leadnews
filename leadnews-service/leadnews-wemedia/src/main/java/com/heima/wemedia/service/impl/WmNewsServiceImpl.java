package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class WmNewsServiceImpl  extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Override
    public ResponseResult findList(WmNewsPageReqDto wmNewsPageReqDto) {
        wmNewsPageReqDto.checkParam();

        LambdaQueryWrapper<WmNews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(wmNewsPageReqDto.getStatus() != null, WmNews::getStatus, wmNewsPageReqDto.getStatus())
                .eq(wmNewsPageReqDto.getChannelId() != null, WmNews::getChannelId, wmNewsPageReqDto.getChannelId())
                .between(wmNewsPageReqDto.getBeginPubDate() != null && wmNewsPageReqDto.getEndPubDate() != null,
                        WmNews::getPublishTime, wmNewsPageReqDto.getBeginPubDate(), wmNewsPageReqDto.getEndPubDate());

        IPage<WmNews> page = page(new Page<>(wmNewsPageReqDto.getPage(), wmNewsPageReqDto.getSize()), wrapper);
        return new PageResponseResult(page);
    }
}
