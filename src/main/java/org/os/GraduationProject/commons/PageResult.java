package org.os.GraduationProject.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 */
@ApiModel(value = "org.os.GraduationProject.commons.PageResult",description = "分页结果")
public class PageResult implements Serializable {
    @ApiModelProperty(name = "total",value = "总记录数")
    private int total;//总记录数
    @ApiModelProperty(name = "rows",value = "当前页结果")
    private List rows;//当前页结果

    public PageResult(int total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
