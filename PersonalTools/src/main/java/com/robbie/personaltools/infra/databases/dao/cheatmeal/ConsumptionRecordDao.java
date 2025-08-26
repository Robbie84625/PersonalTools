package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumptionRecordDao extends JpaRepository<ConsumptionRecord, Long> {
  @Query(
      "SELECT record FROM ConsumptionRecord  record"
          + " WHERE record.userId = :userId"
          + " AND record.consumedAt>= :weekStart"
          + " AND record.consumedAt< :weekEnd")
  List<ConsumptionRecord> findByUserIdAndWeekRange(
      String userId, LocalDateTime weekStart, LocalDateTime weekEnd);
}
