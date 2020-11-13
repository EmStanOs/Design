package org.os.GraduationProject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "org.os.GraduationProject.pojo.CheckGroup",description = "检查组实体类")
public class CheckGroup {
    @ApiModelProperty(name = "id",value = "检查组ID")
    private int id;
    @ApiModelProperty(name = "code",value = "检查组编号")
    private String code;
    @ApiModelProperty(name = "name",value = "检查组名称")
    private String name;
    @ApiModelProperty(name = "helpCode",value = "检查组助记码")
    private String helpCode;
    @ApiModelProperty(name = "sex",value = "适用性别")
    private String sex;
    @ApiModelProperty(name = "remark",value = "备注")
    private String remark;
    @ApiModelProperty(name = "attention",value = "注意事项")
    private String attention;

    @ApiModelProperty(name = "checkItemIds",value = "检查项编号")
    private Integer[] checkItemIds;

    public CheckGroup() {
    }

    public CheckGroup(String code, String name, String helpCode, String sex, String remark, String attention,Integer[] checkItemIds) {
        this.code = code;
        this.name = name;
        this.helpCode = helpCode;
        this.sex = sex;
        this.remark = remark;
        this.attention = attention;
        this.checkItemIds = checkItemIds;
    }

    public Integer[] getCheckItemIds() {
        return checkItemIds;
    }

    public void setCheckItemIds(Integer[] checkItemIds) {
        this.checkItemIds = checkItemIds;
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
}
