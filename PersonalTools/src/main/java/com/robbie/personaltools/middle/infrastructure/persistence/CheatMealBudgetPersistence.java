package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.BudgetSettingsDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealBudgetPersistence {

  private final BudgetSettingsDao budgetSettingsDao;

  public Optional<BudgetSetting> findByCustomerId(String customerId) {
    return this.budgetSettingsDao.findByCustomerId(customerId);
  }
}
