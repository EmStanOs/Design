package org.os.GraduationProject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "org.os.GraduationProject.pojo.CheckItem",description = "检查项实体类")
public class CheckItem {
    @ApiModelProperty(name = "id",value = "检查项ID")
    private int id;
    @ApiModelProperty(name = "code",value = "检查项编号")
    private String code;
    @ApiModelProperty(name = "name",value = "检查项名称")
    private String name;
    @ApiModelProperty(name = "sex",value = "适用性别")
    private int sex;
    @ApiModelProperty(name = "age",value = "适用年龄")
    private String age;
    @ApiModelProperty(name = "price",value = "价格")
    private int price;
    @ApiModelProperty(name = "type",value = "类型")
    private int type;
    @ApiModelProperty(name = "attention",value = "注意事项")
    private String attention;
    @ApiModelProperty(name = "remark",value = "备注")
    private String remark;

    public CheckItem() {
    }

    public CheckItem(String code, String name, int sex, String age, int price, int type, String attention, String remark) {
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.type = type;
        this.attention = attention;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
