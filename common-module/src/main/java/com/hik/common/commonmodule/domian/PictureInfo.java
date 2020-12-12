package com.hik.common.commonmodule.domian;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  图片实体
 */
@Table
@Entity
public class PictureInfo implements Serializable {
    private static final long serialVersionUID = 4013991436455643775L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pic_id;

    @Column
    private String pic_url;

    @Column
    private String pic_name;

    @Column
    private LocalDateTime createTime;

    public PictureInfo() {
    }

    public PictureInfo(String pic_url, String pic_name, LocalDateTime createTime, SnapshotRecord snapshotRecord) {
        this.pic_url = pic_url;
        this.pic_name = pic_name;
        this.createTime = createTime;
        this.snapshotRecord = snapshotRecord;
    }

    @JoinColumn(name="dr_id")
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    public SnapshotRecord snapshotRecord;

    public Long getPic_id() {
        return pic_id;
    }

    public void setPic_id(Long pic_id) {
        this.pic_id = pic_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public SnapshotRecord getSnapshotRecord() {
        return snapshotRecord;
    }

    public void setSnapshotRecord(SnapshotRecord snapshotRecord) {
        this.snapshotRecord = snapshotRecord;
    }

    @Override
    public String toString() {
        return "PictureInfo{" +
                "pic_id=" + pic_id +
                ", pic_url='" + pic_url + '\'' +
                ", pic_name='" + pic_name + '\'' +
                ", createTime=" + createTime +
                ", snapshotRecord=" + snapshotRecord +
                '}';
    }
}
