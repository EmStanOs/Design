package org.os.GraduationProject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
@ApiModel(value = "org.os.GraduationProject.pojo.OrderSetting",description = "预约管理实体类")
public class OrderSetting implements Serializable{
    @ApiModelProperty(name = "id",value = "编号")
    private Integer id ;
    @ApiModelProperty(name = "orderDate",value = "预约日期")
    private Date orderDate;//预约设置日期
    @ApiModelProperty(name = "number",value = "可预约人数")
    private int number;//可预约人数
    @ApiModelProperty(name = "reservations",value = "已预约人数")
    private int reservations ;//已预约人数

    public OrderSetting() {
    }

    public OrderSetting(Date orderDate, int number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }
}
