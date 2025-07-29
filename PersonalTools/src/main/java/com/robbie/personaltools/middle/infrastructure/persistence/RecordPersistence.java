package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.RecordDao;
import com.robbie.personaltools.infra.databases.dao.cheatmeal.RecordMealDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Record;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordPersistence {
  private final RecordDao recordDao;
  private final RecordMealDao recordMealDao;

  public boolean existsByCustomerId(String customerId) {
    return this.recordDao.existsByCustomerId(customerId);
  }

  public List<Record> findByCustomerIdAndDateBetweenStartAtAndEndAt(
      String customerId, LocalDate date) {
    return this.recordDao.findByCustomerIdAndDateBetweenStartAtAndEndAt(customerId, date);
  }

  public Record saveRecord(Record record) {
    return this.recordDao.save(record);
  }

  public void saveRecordMeal(RecordMeal recordMeal) {
    this.recordMealDao.save(recordMeal);
  }

  public List<RecordMeal> findByRecordId(Long recordId) {
    return this.recordMealDao.findByRecordId(recordId);
  }
}
