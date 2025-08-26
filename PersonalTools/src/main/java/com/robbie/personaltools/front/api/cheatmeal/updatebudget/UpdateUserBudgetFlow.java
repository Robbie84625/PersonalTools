package com.robbie.personaltools.front.api.cheatmeal.updatebudget;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserBudgetFlow {
  private final TokenGetter tokenGetter;
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;

  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    LocalDateTime weekStart = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
    LocalDateTime weekEnd = weekStart.plusDays(7);

    // 取得使用者設定的預算
    BudgetSetting budgetSetting =
        this.cheatMealBudgetPersistence
            .findByUserIdAndWeek(userId, weekStart, weekEnd)
            .map(
                existing -> {
                  existing.setBudget(command.getBudget());
                  return existing;
                })
            .orElseGet(
                () -> {
                  BudgetSetting newBudget = new BudgetSetting();
                  newBudget.setUserId(userId);
                  newBudget.setBudget(command.getBudget());
                  newBudget.setCreatedAt(LocalDateTime.now());
                  return newBudget;
                });

    this.cheatMealBudgetPersistence.saveBudgetSetting(budgetSetting);
  }

  @Builder
  @Getter
  public static class Command {
    /** 使用者設定的預算 */
    private Integer budget;
  }
}
