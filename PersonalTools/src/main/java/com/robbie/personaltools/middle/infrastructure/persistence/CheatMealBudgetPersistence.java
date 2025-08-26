package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.BudgetSettingsDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealBudgetPersistence {

  private final BudgetSettingsDao budgetSettingsDao;

  public List<BudgetSetting> findByUserIdAndCreatedAtBefore(String userId, LocalDateTime weekEnd) {
    return this.budgetSettingsDao.findByUserIdAndCreatedAtBefore(userId, weekEnd);
  }

  public Optional<BudgetSetting> findByUserId(String userId) {
    return this.budgetSettingsDao.findByUserId(userId);
  }

  public Optional<BudgetSetting> findByUserIdAndWeek(
      String userId, LocalDateTime weekStart, LocalDateTime weekEnd) {
    return this.budgetSettingsDao.findByUserIdAndWeek(userId, weekStart, weekEnd);
  }

  public void saveBudgetSetting(BudgetSetting budgetSetting) {
    this.budgetSettingsDao.save(budgetSetting);
  }
}
