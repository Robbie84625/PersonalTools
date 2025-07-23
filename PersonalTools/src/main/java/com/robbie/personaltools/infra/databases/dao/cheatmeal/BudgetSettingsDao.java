package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetSettingsDao extends JpaRepository<BudgetSetting, Long> {
  Optional<BudgetSetting> findByCustomerId(String customerId);
}
