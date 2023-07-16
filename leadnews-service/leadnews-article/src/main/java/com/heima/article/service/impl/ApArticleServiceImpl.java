package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.LoadDataEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.List;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:51
 */

@Slf4j
@Service
@Transactional
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Override
    public ResponseResult load(@Valid @ModelAttribute ArticleHomeDto articleHomeDto,
                               LoadDataEnum loadDataEnum) {
        List<ApArticle> articleList = this.baseMapper.loadArticleList(articleHomeDto, loadDataEnum.getCode());
        return ResponseResult.okResult(articleList);
    }

}
