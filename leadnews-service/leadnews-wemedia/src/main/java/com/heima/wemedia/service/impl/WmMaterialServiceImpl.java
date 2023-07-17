package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    private final FileStorageService fileStorageService;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        String filePath;
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "")
                    + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            filePath = fileStorageService.uploadImgFile("", fileName, multipartFile.getInputStream());
        } catch (Exception e) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_IMAGE_FORMAT_ERROR);
        }

        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(filePath);
        wmMaterial.setType(0);
        wmMaterial.setIsCollection(0);
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);

        return ResponseResult.okResult(wmMaterial);
    }

    @Override
    public ResponseResult findList(WmMaterialDto wmMaterialDto) {
        wmMaterialDto.checkParam();
        LambdaQueryWrapper<WmMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(wmMaterialDto.getIsCollection() != null && wmMaterialDto.getIsCollection() == 1,
                WmMaterial::getIsCollection, wmMaterialDto.getIsCollection())
                .eq(WmMaterial::getUserId, WmThreadLocalUtil.getUser().getId())
                        .orderByDesc(WmMaterial::getCreatedTime);

        IPage<WmMaterial> page = page(new Page<>(wmMaterialDto.getPage(), wmMaterialDto.getSize()), wrapper);
        return new PageResponseResult(page);

    }
}
