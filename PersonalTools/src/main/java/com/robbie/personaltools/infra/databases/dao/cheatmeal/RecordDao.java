package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Record;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordDao extends JpaRepository<Record, Long> {
  boolean existsByUserId(String userId);

  @Query("SELECT r FROM Record  r WHERE r.userId =:userId AND :date BETWEEN r.startAt AND r.endAt")
  Optional<Record> findByUserIdAndDateBetweenStartAtAndEndAt(
      @Param("userId") String userId, @Param("date") LocalDate date);
}
