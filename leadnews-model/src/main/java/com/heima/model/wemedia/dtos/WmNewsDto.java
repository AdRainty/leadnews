package com.heima.model.wemedia.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("文章发布")
public class WmNewsDto {

    @ApiModelProperty("ID")
    private Integer id;
     /**
     * 标题
     */
     @ApiModelProperty("标题")
    private String title;
     /**
     * 频道id
     */
     @ApiModelProperty("频道id")
    private Integer channelId;
     /**
     * 标签
     */
     @ApiModelProperty("标签")
    private String labels;
     /**
     * 发布时间
     */
     @ApiModelProperty("发布时间")
    private Date publishTime;
     /**
     * 文章内容
     */
     @NotNull
     @ApiModelProperty("文章内容")
    private String content;
     /**
     * 文章封面类型  0 无图 1 单图 3 多图 -1 自动
     */
     @ApiModelProperty("文章封面类型")
    private Integer type;
     /**
     * 提交时间
     */
     @ApiModelProperty("提交时间")
    private Date submitedTime; 
     /**
     * 状态 提交为1  草稿为0
     */
     @ApiModelProperty("状态")
    private Integer status;
     
     /**
     * 封面图片列表 多张图以逗号隔开
     */
     @ApiModelProperty("封面图片列表")
    private List<String> images;
}