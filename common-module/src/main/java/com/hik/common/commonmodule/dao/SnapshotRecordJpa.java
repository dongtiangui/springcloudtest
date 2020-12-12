package com.hik.common.commonmodule.dao;

import com.hik.common.commonmodule.domian.SnapshotRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 抓拍信息持久dao
 */
public interface SnapshotRecordJpa extends JpaRepository<SnapshotRecord,String> {
}
