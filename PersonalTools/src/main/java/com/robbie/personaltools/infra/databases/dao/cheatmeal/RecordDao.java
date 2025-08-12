package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Record;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordDao extends JpaRepository<Record, Long> {
  boolean existsByCustomerId(String customerId);

  @Query(
      "SELECT r FROM Record  r WHERE r.customerId =:customerId AND :date BETWEEN r.startAt AND r.endAt")
  List<Record> findByCustomerIdAndDateBetweenStartAtAndEndAt(
      @Param("customerId") String customerId, @Param("date") LocalDate date);
}
