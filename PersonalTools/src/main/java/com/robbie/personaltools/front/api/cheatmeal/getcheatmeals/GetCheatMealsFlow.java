package com.robbie.personaltools.front.api.cheatmeal.getcheatmeals;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealPersistence;
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
public class GetCheatMealsFlow {
  private final CheatMealPersistence cheatMealPersistence;

  private final TokenGetter tokenGetter;

  public Page<Result> execute(Command command) throws ValidException {
    Pageable pageable = PageRequest.of(command.getPage(), command.getSize());

    String userId = this.tokenGetter.getTokenInfo().getUserId();

    return this.cheatMealPersistence
        .findMeals(userId, command.getKeyword(), command.getCategory(), pageable)
        .map(this::convertToResult);
  }

  private Result convertToResult(Meal meal) {

    return Result.builder()
        .cheatMealId(meal.getId())
        .cheatMealName(meal.getName())
        .cheatMealPoint(meal.getPoint())
        .cheatMealLevel(meal.getLevel())
        .cheatMealCategory(meal.getCategory())
        .build();
  }

  @Builder
  @Getter
  public static class Command {
    /** 搜尋關鍵字 - 模糊搜尋餐點名稱 */
    private String keyword;

    /** 餐點分類篩選 - 精確匹配分類名稱 */
    private String category;

    /** 分頁頁碼 - 從0開始計算 */
    private Integer page;

    /** 每頁筆數 - 單頁顯示的餐點數量 default 10 */
    private Integer size;
  }

  @Data
  @Builder
  public static class Result {
    /** 作弊餐點 id */
    private Long cheatMealId;

    /** 作弊餐點名稱 */
    private String cheatMealName;

    /** 作弊餐點等級 */
    private Integer cheatMealLevel;

    /** 作弊餐點消耗點數 */
    private Integer cheatMealPoint;

    /** 作弊餐點消耗類別 */
    private String cheatMealCategory;
  }
}
