package com.robbie.personaltools.front.api.cheatmeal.updatebudget;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.RecordPersistence;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserBudgetFlow {
  private final TokenGetter tokenGetter;
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;
  private final RecordPersistence recordPersistence;

  @Transactional
  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 取得使用者設定的預算
    BudgetSetting budgetSetting =
        this.cheatMealBudgetPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    budgetSetting.setBudget(command.getBudget());

    this.cheatMealBudgetPersistence.saveBudgetSetting(budgetSetting);

    this.recordPersistence
        .findByUserIdAndDateBetweenStartAtAndEndAt(userId, LocalDate.now())
        .ifPresent(
            record -> {
              record.setBudget(command.getBudget());
              this.recordPersistence.saveRecord(record);
            });
  }

  @Builder
  @Getter
  public static class Command {
    /** 使用者設定的預算 */
    private Integer budget;
  }

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    USER_NOT_EXIST("用戶不存在");

    private final String errorMessage;

    @Override
    public String getErrorCode() {
      return this.name();
    }

    @Override
    public String getErrorMessage() {
      return this.errorMessage;
    }
  }
}
