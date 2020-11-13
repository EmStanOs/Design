package org.os.GraduationProject.commons;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 封装查询条件
 */
@ApiModel(value = "org.os.GraduationProject.commons.QueryPageBean",description = "查询条件对象")
public class QueryPageBean implements Serializable{
    @ApiModelProperty(name = "currentPage",value = "页码")
    private Integer currentPage;//页码
    @ApiModelProperty(name = "pageSize",value = "每页记录数")
    private Integer pageSize;//每页记录数
    @ApiModelProperty(name = "queryString",value = "查询条件")
    private String queryString;//查询条件

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}