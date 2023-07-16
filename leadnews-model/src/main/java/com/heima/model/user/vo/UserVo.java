package com.heima.model.user.vo;

import com.heima.model.user.pojos.ApUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 17:56
 */

@Data
@NoArgsConstructor
public class UserVo {

    private Integer id;

    private String name;

    private String phone;

    public UserVo(ApUser apUser) {
        BeanUtils.copyProperties(apUser, this);
    }

}
