package com.robbie.backend.front.api.cheatmeal.updateusername;

import com.robbie.backend.front.api.cheatmeal.updateusername.UpdateUserNameFlow.Command;
import com.robbie.backend.front.api.cheatmeal.updateusername.model.UpdateUserNameRequest;
import com.robbie.backend.infra.exception.ValidException;
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
