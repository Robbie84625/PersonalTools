package com.robbie.personaltools.middle.domain.cheatmeal.repository;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import java.util.Optional;

public interface CheatMealBudgetRepository {
  Optional<BudgetSetting> findByCustomerId(String customerId);
}
