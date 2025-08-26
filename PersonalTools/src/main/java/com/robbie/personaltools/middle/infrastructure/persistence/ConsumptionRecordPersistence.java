package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.ConsumptionRecordDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumptionRecordPersistence {

  private final ConsumptionRecordDao consumptionRecordDao;

  public List<ConsumptionRecord> findByUserIdAndWeekRange(
      String userId, LocalDateTime weekStart, LocalDateTime weekEnd) {
    return this.consumptionRecordDao.findByUserIdAndWeekRange(userId, weekStart, weekEnd);
  }

  public void saveConsumptionRecord(ConsumptionRecord record) {
    this.consumptionRecordDao.save(record);
  }
}
