package com.robbie.infra.databases.dao.cheatmeal;

import com.robbie.infra.databases.entity.cheatmeal.RecordMeal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordMealDao extends JpaRepository<RecordMeal, Long> {
  List<RecordMeal> findByRecordId(Long recordId);

  List<RecordMeal> findByRecordIdIn(List<Long> recordIds);
}
