package com.robbie.front.api.cheatmeal.updateusername;

import com.robbie.front.api.cheatmeal.updateusername.UpdateUserNameFlow.Command;
import com.robbie.front.api.cheatmeal.updateusername.model.UpdateUserNameRequest;
import com.robbie.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("updateUserNamePresentation")
@RequiredArgsConstructor
public class UpdateUserNamePresentation {
  private final UpdateUserNameFlow updateUserNameFlow;

  public void execute(UpdateUserNameRequest request) throws ValidException {
    this.updateUserNameFlow.execute(Command.builder().userName(request.getUserName()).build());
  }
}
