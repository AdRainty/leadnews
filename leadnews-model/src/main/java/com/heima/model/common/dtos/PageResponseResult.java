package com.heima.model.common.dtos;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseResult extends ResponseResult implements Serializable {
    private Long currentPage;
    private Long size;
    private Long total;

    public PageResponseResult(IPage page) {
        this.currentPage = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.setData(page.getRecords());
    }
}
