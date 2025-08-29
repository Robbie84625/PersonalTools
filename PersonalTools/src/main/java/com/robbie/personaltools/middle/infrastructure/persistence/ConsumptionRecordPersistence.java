package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.ConsumptionRecordDao;
import com.robbie.personaltools.infra.databases.dao.cheatmeal.RecordMealDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumptionRecordPersistence {

  private final ConsumptionRecordDao consumptionRecordDao;
  private final RecordMealDao recordMealDao;

  public Page<ConsumptionRecord> findByUserIdOrderByCreatedAtDesc(
      String userId, Pageable pageable) {
    return this.consumptionRecordDao.findByUserIdOrderByCreatedAtDesc(userId, pageable);
  }

  public Long saveConsumptionRecord(ConsumptionRecord record) {
    ConsumptionRecord savedRecord = this.consumptionRecordDao.save(record);
    return savedRecord.getId();
  }

  public List<RecordMeal> findByRecordId(Long recordId) {
    return this.recordMealDao.findByRecordId(recordId);
  }

  public void saveRecordMeal(RecordMeal recordMeal) {
    this.recordMealDao.save(recordMeal);
  }
}
