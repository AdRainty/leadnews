package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.wemedia.service.WmMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/17 21:15
 */

@Api("自媒体接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/material")
public class WmMaterialController {

    private final WmMaterialService wmMaterialService;

    @PostMapping("/upload_picture")
    @ApiOperation("上传图片")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }

    @PostMapping("/list")
    @ApiOperation("素材列表查询")
    public ResponseResult findList(@RequestBody WmMaterialDto wmMaterialDto) {
        return wmMaterialService.findList(wmMaterialDto);
    }

}
