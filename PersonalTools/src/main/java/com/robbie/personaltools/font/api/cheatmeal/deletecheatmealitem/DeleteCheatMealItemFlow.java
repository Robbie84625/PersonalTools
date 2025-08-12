package com.robbie.personaltools.font.api.cheatmeal.deletecheatmealitem;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCheatMealItemFlow {
  private final CheatMealPersistence cheatMealPersistence;

  @Value("{customer.id}")
  private String customerId;

  public void execute(Long cheatMealId) throws ValidException {

    int deletedRows =
        this.cheatMealPersistence.deleteByCustomerIdAndId(this.customerId, cheatMealId);
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
