package com.robbie.personaltools.front.api.cheatmeal.updateusername;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.user.Account;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.AccountPersistence;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserNameFlow {

  private final TokenGetter tokenGetter;
  private final AccountPersistence accountPersistence;

  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    Account account =
        this.accountPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    if (StringUtils.equals(command.getUserName(), account.getName())) {
      throw new ValidException(ErrorCodeEnum.DUPLICATE_USERNAME);
    }

    account.setName(command.getUserName());
    account.setUpdatedAt(LocalDateTime.now());

    this.accountPersistence.saveAccount(account);
  }

  @Builder
  @Getter
  public static class Command {
    /** 使用者名稱 */
    private String userName;
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
