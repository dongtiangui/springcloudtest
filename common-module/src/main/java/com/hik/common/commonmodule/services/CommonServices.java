package com.hik.common.commonmodule.services;

import com.hik.common.commonmodule.domian.PictureInfo;
import com.hik.common.commonmodule.domian.SnapshotRecord;

import java.util.List;
import java.util.Map;

/**
 * 公共服务接口
 */
public interface CommonServices {

    public List<String> getImageUrl();

    public List<String> getImageUrlByDr(String dr_id);

    public String getImageUrlByName(String picName);

    public PictureInfo insertImage(PictureInfo pictureInfo);

    public String insertSR(Map<String,Object> param);

    public List<SnapshotRecord> selectSRAll();

    public SnapshotRecord selectRSById(String d_id);

    public boolean deleteSRById(String d_id);

    public boolean deleteSRByListId(String[] ids);

}
