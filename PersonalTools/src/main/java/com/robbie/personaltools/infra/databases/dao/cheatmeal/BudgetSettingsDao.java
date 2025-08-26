package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BudgetSettingsDao extends JpaRepository<BudgetSetting, Long> {
  Optional<BudgetSetting> findByUserId(String userId);

  @Query(
      "SELECT b FROM BudgetSetting b WHERE b.userId = :userId "
          + "AND b.createdAt >= :weekStart AND b.createdAt < :weekEnd")
  Optional<BudgetSetting> findByUserIdAndWeek(
      @Param("userId") String userId,
      @Param("weekStart") LocalDateTime weekStart,
      @Param("weekEnd") LocalDateTime weekend);

  @Query(
      "SELECT b FROM BudgetSetting b WHERE b.userId = :userId "
          + "AND b.createdAt < :weekEnd "
          + "ORDER BY b.createdAt DESC")
  List<BudgetSetting> findByUserIdAndCreatedAtBefore(
      @Param("userId") String userId, @Param("weekEnd") LocalDateTime weekEnd);
}
