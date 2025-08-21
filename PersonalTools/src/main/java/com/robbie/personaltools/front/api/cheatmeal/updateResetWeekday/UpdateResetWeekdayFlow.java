package com.robbie.personaltools.front.api.cheatmeal.updateResetWeekday;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateResetWeekdayFlow {
  private final TokenGetter tokenGetter;
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;

  @Transactional
  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 取得使用者預算設定，並更新重置週期
    BudgetSetting budgetSetting =
        this.cheatMealBudgetPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    budgetSetting.setResetWeekday(command.getResetWeekday());
    this.cheatMealBudgetPersistence.saveBudgetSetting(budgetSetting);
  }

  @Builder
  @Getter
  public static class Command {
    /** 使用者名稱 */
    private Integer resetWeekday;
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
