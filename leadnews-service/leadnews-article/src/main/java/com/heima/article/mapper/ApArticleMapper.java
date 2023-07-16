package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:44
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    /**
     * 加载文章列表
     * @param articleHomeDto
     * @param type
     * @return
     */
    List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto articleHomeDto,
                                    @Param("type") Integer type);

}
