package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/17 22:45
 */

@Api("频道信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/channel")
public class WmChannelController {

    private final WmChannelService wmChannelService;

    @GetMapping("/channels")
    @ApiOperation("查询所有频道")
    public ResponseResult findAll() {
        return wmChannelService.findAll();
    }

}
