package com.robbie.personaltools.front.api.cheatmeal.updatebudget;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.user.Account;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.AccountPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import jakarta.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserBudgetFlow {
  private final TokenGetter tokenGetter;
  private final AccountPersistence accountPersistence;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  @Transactional
  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();
    LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);

    Account account =
        this.accountPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    if (account.getBudget().equals(command.getBudget())) {
      return;
    }

    account.setBudget(command.getBudget());
    this.accountPersistence.saveAccount(account);

    Pageable pageable = PageRequest.of(0, 1);

    this.consumptionRecordPersistence.findByUserIdOrderByCreatedAtDesc(userId, pageable).stream()
        .filter(record -> record.getWeekStart().isEqual(weekStart))
        .findFirst()
        .ifPresent(
            record -> {
              record.setBudget(command.getBudget());
              this.consumptionRecordPersistence.saveConsumptionRecord(record);
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
    USER_NOT_EXIST("用戶不存在"),
    DUPLICATE_USERNAME("使用者名稱重複新增");

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
