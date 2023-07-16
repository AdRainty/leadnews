package com.heima.model.user.result;

import com.heima.model.user.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:56
 */

@Data
@AllArgsConstructor
public class LoginResult {

    private String token;

    private UserVo user;



}
