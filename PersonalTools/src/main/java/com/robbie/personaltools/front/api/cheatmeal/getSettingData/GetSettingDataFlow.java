package com.robbie.personaltools.front.api.cheatmeal.getSettingData;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.user.Account;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.AccountPersistence;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetSettingDataFlow {

  private final AccountPersistence accountPersistence;

  private final TokenGetter tokenGetter;

  public Result execute() throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 取得使用者資料
    Account user =
        this.accountPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    return Result.builder()
        .userName(user.getName())
        .userType(user.getUserType())
        .email(user.getEmail())
        .budget(user.getBudget())
        .resetWeekday(7)
        .build();
  }

  @Data
  @Builder
  public static class Result {
    /** 使用者名稱 */
    private String userName;

    /** 使用者 Type */
    private String userType;

    /** Google 電子郵件 */
    private String email;

    /** 使用者設定預算 */
    private Integer budget;

    /** 每周重置日 */
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
