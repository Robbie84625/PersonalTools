package com.robbie.personaltools.front.api.cheatmeal.createrecord;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRecordFlow {

  private final TokenGetter tokenGetter;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    ConsumptionRecord consumptionRecord = this.createRecord(command, userId);

    this.consumptionRecordPersistence.saveConsumptionRecord(consumptionRecord);
  }

  private ConsumptionRecord createRecord(Command command, String userId) {
    ConsumptionRecord consumptionRecord = new ConsumptionRecord();
    consumptionRecord.setMealName(command.getCheatMealName());
    consumptionRecord.setMealPoint(command.getCheatMealPoint());
    consumptionRecord.setUserId(userId);
    return consumptionRecord;
  }

  @Builder
  @Getter
  public static class Command {
    /** 作弊餐點名稱 */
    private String cheatMealName;

    /** 作弊餐點消耗點數 */
    private Integer cheatMealPoint;

    /** 總預算 */
    private Integer totalBudget;

    /** 星期幾重置 */
    private Integer resetWeekday;
  }
}
