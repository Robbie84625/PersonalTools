package com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetCheatMealsResponse {
  /** 作弊餐點資料列表 */
  private List<CheatMeal> cheatMeals;

  /** 分頁資訊 */
  private PaginationInfo pagination;

  @Builder
  @Data
  public static class CheatMeal {
    /** 作弊餐點 id */
    private Long id;

    /** 作弊餐點名稱 */
    private String name;

    /** 作弊餐點等級 */
    private Integer level;

    /** 作弊餐點消耗點數 */
    private Integer point;

    /** 作弊餐點消耗類別 */
    private String category;
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
