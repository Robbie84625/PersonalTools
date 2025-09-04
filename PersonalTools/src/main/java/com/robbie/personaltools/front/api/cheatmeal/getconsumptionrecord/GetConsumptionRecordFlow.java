package com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord;

import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.GetConsumptionRecordFlow.Result.CheatMeal;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetConsumptionRecordFlow {
  private final TokenGetter tokenGetter;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  public Page<Result> execute(Command command) throws ValidException {
    Pageable pageable = PageRequest.of(command.getPage(), command.getSize());

    String userId = this.tokenGetter.getTokenInfo().getUserId();

    Page<ConsumptionRecord> consumptionRecords =
        this.consumptionRecordPersistence.findByUserIdOrderByCreatedAtDesc(userId, pageable);

    if (consumptionRecords.getContent().isEmpty()) {
      return Page.empty(pageable);
    }

    List<Long> recordIds =
        consumptionRecords.getContent().stream().map(ConsumptionRecord::getId).toList();

    Map<Long, List<RecordMeal>> recordMealsMap =
        this.consumptionRecordPersistence.findByRecordIdIn(recordIds).stream()
            .collect(Collectors.groupingBy(RecordMeal::getRecordId));

    return consumptionRecords.map(
        record -> {
          // 取得該記錄對應的餐點清單
          List<RecordMeal> recordMeals = recordMealsMap.getOrDefault(record.getId(), List.of());

          // 計算總消費點數
          int totalConsumption = recordMeals.stream().mapToInt(RecordMeal::getMealPoint).sum();

          // 判斷狀態 (是否已結束)
          boolean status = LocalDate.now().isAfter(record.getWeekEnd());

          // 建立餐點詳細資訊清單
          List<CheatMeal> cheatMeals =
              recordMeals.stream()
                  .map(
                      recordMeal ->
                          CheatMeal.builder()
                              .mealName(recordMeal.getMealName())
                              .mealPoint(recordMeal.getMealPoint())
                              .build())
                  .toList();

          // 建立 Result 物件
          return Result.builder()
              .startDate(record.getWeekStart())
              .endDate(record.getWeekEnd())
              .budget(record.getBudget())
              .totalConsumption(totalConsumption)
              .status(status)
              .cheatMealList(cheatMeals)
              .build();
        });
  }

  @Builder
  @Getter
  public static class Command {
    /** 分頁頁碼 - 從0開始計算 */
    private Integer page;

    /** 每頁筆數 - 單頁顯示的餐點數量 default 20 */
    private Integer size;
  }

  @Builder
  @Data
  public static class Result {
    /** 起始日期 */
    private LocalDate startDate;

    /** 結束日期 */
    private LocalDate endDate;

    /** 預算 */
    private Integer budget;

    /** 總消費 */
    private Integer totalConsumption;

    /** 紀錄狀態 */
    private boolean status;

    /** 作弊餐列表 */
    private List<CheatMeal> cheatMealList;

    @Builder
    @Data
    public static class CheatMeal {
      /** 餐點名稱 */
      private String mealName;

      /** 餐點點數 */
      private Integer mealPoint;
    }
  }
}
