package com.robbie.personaltools.front.api.cheatmeal.createrecord;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRecordFlow {

  private final TokenGetter tokenGetter;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);
    LocalDate weekEnd = weekStart.plusDays(6);

    Pageable pageable = PageRequest.of(0, 1);
    Optional<ConsumptionRecord> existingRecord =
        this.consumptionRecordPersistence
            .findByUserIdOrderByCreatedAtDesc(userId, pageable)
            .stream()
            .filter(record -> record.getWeekStart().isEqual(weekStart))
            .findFirst();

    Long recordId;
    if (existingRecord.isPresent()) {
      recordId = existingRecord.get().getId();
    } else {
      ConsumptionRecord newConsumptionRecord = new ConsumptionRecord();
      newConsumptionRecord.setUserId(userId);
      newConsumptionRecord.setWeekStart(weekStart);
      newConsumptionRecord.setWeekEnd(weekEnd);
      newConsumptionRecord.setBudget(command.getTotalBudget());
      newConsumptionRecord.setCreatedAt(LocalDateTime.now());

      recordId = this.consumptionRecordPersistence.saveConsumptionRecord(newConsumptionRecord);
    }

    RecordMeal recordMeal = new RecordMeal();
    recordMeal.setRecordId(recordId);
    recordMeal.setMealName(command.getCheatMealName());
    recordMeal.setMealPoint(command.getCheatMealPoint());
    recordMeal.setConsumedAt(LocalDateTime.now());
    this.consumptionRecordPersistence.saveRecordMeal(recordMeal);
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
