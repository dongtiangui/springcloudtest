package com.hik.common.commonmodule.domian;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 抓拍记录
 */
@Table
@Entity
public class SnapshotRecord implements Serializable {
    private static final long serialVersionUID = -920902800317414737L;
    /**
     * 设备ID
     */
    @Id
    @Column(name = "d_id")
    private String d_id;

    /**
     * 设备安装地点
     */
    @Column
    private String d_local;

    /**
     * 设备类型
     */
    @Column
    private String d_type;

    /**
     * 设备抓拍时间
     */
    @Column
    private LocalDateTime createTime;

    /**
     * 抓拍图片地址
     */
    @Column
    private String image_url;

    @OneToMany(mappedBy = "snapshotRecord",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<PictureInfo> pictureInfos;

    public SnapshotRecord(){}

    public SnapshotRecord(String d_id, String d_local, String d_type, LocalDateTime createTime, String image_url) {
        this.d_id = d_id;
        this.d_local = d_local;
        this.d_type = d_type;
        this.createTime = createTime;
        this.image_url = image_url;
    }

    public SnapshotRecord(String d_id, String d_local, String d_type, LocalDateTime createTime, String image_url, List<PictureInfo> pictureInfos) {
        this.d_id = d_id;
        this.d_local = d_local;
        this.d_type = d_type;
        this.createTime = createTime;
        this.image_url = image_url;
        this.pictureInfos = pictureInfos;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_local() {
        return d_local;
    }

    public void setD_local(String d_local) {
        this.d_local = d_local;
    }

    public String getD_type() {
        return d_type;
    }

    public void setD_type(String d_type) {
        this.d_type = d_type;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<PictureInfo> getPictureInfos() {
        return pictureInfos;
    }

    public void setPictureInfos(List<PictureInfo> pictureInfos) {
        this.pictureInfos = pictureInfos;
    }

    @Override
    public String toString() {
        return "SnapshotRecord{" +
                "d_id='" + d_id + '\'' +
                ", d_local='" + d_local + '\'' +
                ", d_type='" + d_type + '\'' +
                ", createTime=" + createTime +
                ", image_url='" + image_url + '\'' +
                ", pictureInfos=" + pictureInfos +
                '}';
    }
}
