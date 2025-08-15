package com.robbie.personaltools.front.api.cheatmeal.deletecheatmealitem;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCheatMealItemFlow {
  private final CheatMealPersistence cheatMealPersistence;

  private final TokenGetter tokenGetter;

  public void execute(Long cheatMealId) throws ValidException {
    String memberId = this.tokenGetter.getTokenInfo().getMemberId();

    int deletedRows = this.cheatMealPersistence.deleteByCustomerIdAndId(memberId, cheatMealId);
    if (deletedRows == 0) {
      throw new ValidException(ErrorCodeEnum.DELETE_ERROR);
    }
  }

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    DELETE_ERROR("刪除失敗");

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
