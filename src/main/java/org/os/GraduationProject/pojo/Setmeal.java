package org.os.GraduationProject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "org.os.GraduationProject.pojo.Setmeal",description = "套餐实体类")
public class Setmeal {
    @ApiModelProperty(name = "id",value = "套餐ID")
    private int id;
    @ApiModelProperty(name = "name",value = "套餐名称")
    private String name;
    @ApiModelProperty(name = "code",value = "套餐编号")
    private String code;
    @ApiModelProperty(name = "helpCode",value = "助记码")
    private String helpCode;
    @ApiModelProperty(name = "sex",value = "适用性别")
    private String sex;
    @ApiModelProperty(name = "age",value = "适用年龄")
    private String age;
    @ApiModelProperty(name = "price",value = "价格")
    private int price;
    @ApiModelProperty(name = "remark",value = "备注")
    private String remark;
    @ApiModelProperty(name = "attention",value = "注意事项")
    private String attention;
    @ApiModelProperty(name = "img",value = "套餐图片")
    private String img;

    private Integer[] checkGroupIds;

    public Setmeal() {
    }

    public Setmeal(String name, String code, String helpCode, String sex, String age, int price, String remark, String attention, String img, Integer[] checkGroupIds) {
        this.name = name;
        this.code = code;
        this.helpCode = helpCode;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.remark = remark;
        this.attention = attention;
        this.img = img;
        this.checkGroupIds = checkGroupIds;
    }

    public Integer[] getCheckGroupIds() {
        return checkGroupIds;
    }

    public void setCheckGroupIds(Integer[] checkGroupIds) {
        this.checkGroupIds = checkGroupIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
