package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRecordDao extends JpaRepository<ConsumptionRecord, Long> {

  Page<ConsumptionRecord> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
}
