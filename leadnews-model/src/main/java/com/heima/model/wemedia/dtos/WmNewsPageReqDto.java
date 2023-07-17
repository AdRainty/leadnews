package com.heima.model.wemedia.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询文章列表")
public class WmNewsPageReqDto extends PageRequestDto {

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date beginPubDate;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    @ApiModelProperty("所属频道ID")
    private Integer channelId;
    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    private String keyword;
}