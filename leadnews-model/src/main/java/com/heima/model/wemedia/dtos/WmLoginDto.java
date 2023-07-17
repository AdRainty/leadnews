package com.heima.model.wemedia.dtos;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("自媒体登陆")
public class WmLoginDto {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("密码")
    private String password;
}
