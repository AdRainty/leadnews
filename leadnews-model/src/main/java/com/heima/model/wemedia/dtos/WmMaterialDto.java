package com.heima.model.wemedia.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@ApiModel("素材列表查询")
@EqualsAndHashCode(callSuper = true)
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 未收藏
     */
    @ApiModelProperty("是否收藏")
    private Integer isCollection;
}