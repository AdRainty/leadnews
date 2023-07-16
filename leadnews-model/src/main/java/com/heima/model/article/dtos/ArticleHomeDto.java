package com.heima.model.article.dtos;

import com.heima.model.common.constants.NewsConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 20:36
 */

@Data
@ApiModel
public class ArticleHomeDto {

    @NotNull
    @ApiModelProperty("最大时间")
    private Date maxBehotTime;

    @NotNull
    @ApiModelProperty("最小时间")
    private Date minBehotTime;

    @Max(NewsConstant.MAX_PAGE_SIZE)
    @NotNull
    @ApiModelProperty("分页size")
    private Integer size;

    @NotNull
    @ApiModelProperty("频道ID")
    private String tag;

    public ArticleHomeDto() {
        this.size = 10;
        this.tag = NewsConstant.DEFAULT_TAG;
        this.maxBehotTime = new Date();
        this.minBehotTime = new Date();
    }

}
