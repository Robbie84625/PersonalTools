package com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetConsumptionRecordResponse {
  private List<ConsumptionRecord> consumptionRecords;

  /** 分頁資訊 */
  private PaginationInfo pagination;

  @Builder
  @Data
  public static class ConsumptionRecord {
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
  }

  @Builder
  @Data
  public static class CheatMeal {
    /** 餐點名稱 */
    private String mealName;

    /** 餐點點數 */
    private Integer mealPoint;
  }

  @Builder
  @Data
  public static class PaginationInfo {
    /** 目前頁數 */
    private int currentPage;

    /** 總頁數 */
    private int totalPages;

    /** 總筆數 */
    private long totalCount;

    /** 每頁筆數 */
    private int pageSize;

    /** 是否有下一頁 */
    private boolean hasNext;

    /** 是否有上一頁 */
    private boolean hasPrevious;

    /** 是否第一頁 */
    private boolean isFirstPage;

    /** 是否最後一頁 */
    private boolean isLastPage;
  }
}
