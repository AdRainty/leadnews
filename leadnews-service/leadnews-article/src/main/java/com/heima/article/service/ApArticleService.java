package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.article.enums.LoadDataEnum;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:51
 */
public interface ApArticleService extends IService<ApArticle> {

    /**
     * 加载文章列表
     * @param articleHomeDto
     * @param loadDataEnum
     * @return
     */
    ResponseResult load(ArticleHomeDto articleHomeDto, LoadDataEnum loadDataEnum);
}
