package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Consumption;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsumptionDao extends JpaRepository<Consumption, Long> {
  @Query(
      "SELECT c FROM Consumption c WHERE c.customerId =:customerId AND c.consumedAt >= :start AND c.consumedAt < :end")
  List<Consumption> findByCustomerIdAndDateRange(
      @Param("customerId") String customerId,
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);
}
