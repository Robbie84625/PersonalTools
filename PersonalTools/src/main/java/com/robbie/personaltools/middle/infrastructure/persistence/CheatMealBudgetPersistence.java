package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.BudgetSettingsDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealBudgetRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealBudgetPersistence implements CheatMealBudgetRepository {

  private final BudgetSettingsDao budgetSettingsDao;

  @Override
  public Optional<BudgetSetting> findByCustomerId(String customerId) {
    return this.budgetSettingsDao.findByCustomerId(customerId);
  }
}
