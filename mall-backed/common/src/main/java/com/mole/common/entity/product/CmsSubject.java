package com.mole.common.entity.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

@Schema(description = "专题实体类")
public class CmsSubject implements Serializable {

    @Schema(description = "唯一标识ID")
    private Long id;

    @Schema(description = "类别ID")
    private Long categoryId;

    @Schema(description = "专题标题")
    private String title;

    @Schema(description = "专题主图")
    private String pic;

    @Schema(description = "关联产品数量")
    private Integer productCount;

    @Schema(description = "推荐状态")
    private Integer recommendStatus;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "收藏数")
    private Integer collectCount;

    @Schema(description = "阅读数")
    private Integer readCount;

    @Schema(description = "评论数")
    private Integer commentCount;

    @Schema(description = "画册图片，用逗号分隔")
    private String albumPics;

    @Schema(description = "专题描述")
    private String description;

    @Schema(description = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @Schema(description = "转发数")
    private Integer forwardCount;

    @Schema(description = "专题分类名称")
    private String categoryName;

    @Schema(description = "专题内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", pic=").append(pic);
        sb.append(", productCount=").append(productCount);
        sb.append(", recommendStatus=").append(recommendStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", collectCount=").append(collectCount);
        sb.append(", readCount=").append(readCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", albumPics=").append(albumPics);
        sb.append(", description=").append(description);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", forwardCount=").append(forwardCount);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}