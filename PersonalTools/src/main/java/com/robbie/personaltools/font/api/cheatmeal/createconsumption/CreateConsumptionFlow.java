package com.robbie.personaltools.font.api.cheatmeal.createconsumption;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Consumption;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.ConsumptionRepository;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateConsumptionFlow {

  private final ConsumptionRepository consumptionRepository;

  @Value("{customer.id}")
  private String customerId;

  public void execute(Command command) throws ValidException {
    Consumption consumption = new Consumption();
    consumption.setCustomerId(this.customerId);
    consumption.setMealName(command.getCheatMealName());
    consumption.setPointsConsumed(command.getCheatMealPoint());
    consumption.setConsumedAt(LocalDateTime.now());

    this.consumptionRepository.saveConsumption(consumption);
  }

  @Builder
  @Getter
  public static class Command {
    /** 作弊餐點名稱 */
    private String cheatMealName;

    /** 作弊餐點消耗點數 */
    private Integer cheatMealPoint;
  }
}
