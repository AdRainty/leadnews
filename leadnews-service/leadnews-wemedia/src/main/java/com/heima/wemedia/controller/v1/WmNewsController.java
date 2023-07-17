package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.wemedia.service.WmNewsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/17 22:58
 */

@Api("文章信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class WmNewsController {

    private final WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmNewsPageReqDto wmNewsPageReqDto) {
        return wmNewsService.findList(wmNewsPageReqDto);
    }

}
