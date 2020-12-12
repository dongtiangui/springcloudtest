package com.hik.common.commonmodule.dao;

import com.hik.common.commonmodule.domian.PictureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 图片持久dao
 */
public interface PictureJpa extends JpaRepository<PictureInfo,Long> {

    @Query("select p from PictureInfo p join p.snapshotRecord u  where  u.d_id=?1")
    public List<PictureInfo> findPictureInfosBySnapshotRecord_D_id(String dr_id);

    @Query("select p from PictureInfo p where p.pic_name=?1")
    public PictureInfo findByPic_name(String name);

}
