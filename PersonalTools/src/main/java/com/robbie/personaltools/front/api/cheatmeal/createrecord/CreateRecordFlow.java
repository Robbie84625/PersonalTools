package com.robbie.personaltools.front.api.cheatmeal.createrecord;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Record;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.RecordPersistence;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRecordFlow {

  private final RecordPersistence recordPersistence;

  private final TokenGetter tokenGetter;

  public void execute(Command command) throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 判斷是否為新使用者
    boolean isNewUser = !this.recordPersistence.existsByUserId(userId);

    // 查詢該使用者這週是否已有記錄
    List<Record> thisWeekRecords =
        this.recordPersistence.findByUserIdAndDateBetweenStartAtAndEndAt(userId, LocalDate.now());

    Long recordId = this.determineRecordByUserState(isNewUser, userId, thisWeekRecords, command);

    RecordMeal recordMeal = this.createRecordMeal(command, recordId);
    this.recordPersistence.saveRecordMeal(recordMeal);
  }

  private Long determineRecordByUserState(
      boolean isNewUser, String userId, List<Record> thisWeekRecords, Command command) {
    if (isNewUser) {
      LocalDate startAt = LocalDate.now();
      LocalDate endAt =
          LocalDate.now()
              .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(command.getResetWeekday())));
      return this.createRecord(command, userId, startAt, endAt);
    } else if (thisWeekRecords.isEmpty()) {
      LocalDate startAt =
          LocalDate.now()
              .with(TemporalAdjusters.previousOrSame(DayOfWeek.of(command.getResetWeekday())))
              .plusDays(1);
      LocalDate endAt =
          LocalDate.now()
              .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(command.getResetWeekday())));
      return this.createRecord(command, userId, startAt, endAt);
    } else {
      return thisWeekRecords.get(0).getId();
    }
  }

  private Long createRecord(Command command, String userId, LocalDate startAt, LocalDate endAt) {
    Record record = new Record();
    record.setUserId(userId);
    record.setBudget(command.getTotalBudget());
    record.setStartAt(startAt);
    record.setEndAt(endAt);
    return this.recordPersistence.saveRecord(record).getId();
  }

  private RecordMeal createRecordMeal(Command command, Long recordId) {
    RecordMeal recordMeal = new RecordMeal();
    recordMeal.setRecordId(recordId);
    recordMeal.setMealName(command.getCheatMealName());
    recordMeal.setMealPoint(command.getCheatMealPoint());
    recordMeal.setConsumedAt(LocalDateTime.now());
    return recordMeal;
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
