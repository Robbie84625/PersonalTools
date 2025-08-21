package com.robbie.personaltools.front.api.cheatmeal.updateusername;

import com.robbie.personaltools.front.api.cheatmeal.updateusername.UpdateUserNameFlow.Command;
import com.robbie.personaltools.front.api.cheatmeal.updateusername.model.UpdateUserNameRequest;
import com.robbie.personaltools.infra.exception.ValidException;
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
