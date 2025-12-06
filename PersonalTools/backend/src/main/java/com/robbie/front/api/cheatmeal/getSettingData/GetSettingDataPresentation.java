package com.robbie.backend.front.api.cheatmeal.getSettingData;

import com.robbie.backend.front.api.cheatmeal.getSettingData.GetSettingDataFlow.Result;
import com.robbie.backend.front.api.cheatmeal.getSettingData.model.GetSettingDataResponse;
import com.robbie.backend.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("getSettingDataPresentation")
@RequiredArgsConstructor
public class GetSettingDataPresentation {
  private final GetSettingDataFlow getSettingDataFlow;

  public GetSettingDataResponse execute() throws ValidException {
    Result result = this.getSettingDataFlow.execute();

    return GetSettingDataResponse.builder()
        .userName(result.getUserName())
        .userType(result.getUserType())
        .email(result.getEmail())
        .budget(result.getBudget())
        .resetWeekday(result.getResetWeekday())
        .build();
  }
}
