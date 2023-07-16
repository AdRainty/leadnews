package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:45
 */
public interface ApUserService extends IService<ApUser> {

    /**
     * App端登陆接口
     * @param loginDto
     * @return
     */
    ResponseResult login(LoginDto loginDto);
}
