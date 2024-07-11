package com.mole.common.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "商品优选区域实体类")
public class CmsPrefrenceArea implements Serializable {

    @Schema(description = "唯一标识ID")
    private Long id;

    @Schema(description = "专区名称")
    private String name;

    @Schema(description = "副标题")
    private String subTitle;

    @Schema(description = "排序序号")
    private Integer sort;

    @Schema(description = "显示状态，1-显示，0-隐藏")
    private Integer showStatus;

    @Schema(description = "展示图片的二进制数据", example = "Base64EncodedImageHere")
    private byte[] pic;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", sort=").append(sort);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", pic=").append(pic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public CmsPrefrenceArea(Long id, String name, String subTitle, Integer sort, Integer showStatus, byte[] pic) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.sort = sort;
        this.showStatus = showStatus;
        this.pic = pic;
    }
    public CmsPrefrenceArea() {
    }
}
