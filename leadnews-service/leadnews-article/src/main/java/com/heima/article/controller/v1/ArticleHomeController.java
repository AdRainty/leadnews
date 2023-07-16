package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.LoadDataEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:34
 */

@Api
@RequestMapping("/api/v1/article")
@RestController
@AllArgsConstructor
public class ArticleHomeController {

    private final ApArticleService apArticleService;

    @ApiOperation("加载首页")
    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, LoadDataEnum.LOAD_MORE);
    }

    @ApiOperation("加载更多")
    @PostMapping("/loadmore")
    public ResponseResult loadmore(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, LoadDataEnum.LOAD_MORE);
    }

    @ApiOperation("加载最新")
    @PostMapping("/loadnew")
    public ResponseResult loadnew(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, LoadDataEnum.LOAD_NEW);
    }

}
