package com.robbie.personaltools.font.api.cheatmeal.getcheatmeals;

import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.GetCheatMealsFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.GetCheatMealsFlow.Result;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.model.GetCheatMealsResponse;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("GetCheatMealsPresentation")
public class GetCheatMealsPresentation {
  private final GetCheatMealsFlow getCheatMealsFlow;

  public GetCheatMealsResponse execute(String keyword, String category, Integer page, Integer size) {
    Page<Result> result =
        this.getCheatMealsFlow.execute(
            Command.builder().keyword(keyword).category(category).page(page).size(size).build());

    return GetCheatMealsResponse.builder()
        .cheatMeals(
            result.getContent().stream()
                .map(
                    item ->
                        GetCheatMealsResponse.CheatMeal.builder()
                            .id(item.getCheatMealId())
                            .name(item.getCheatMealName())
                            .level(item.getCheatMealLevel())
                            .point(item.getCheatMealPoint())
                            .category(item.getCheatMealCategory())
                            .build())
                .collect(Collectors.toList()))
        .pagination(
            GetCheatMealsResponse.PaginationInfo.builder()
                .currentPage(result.getNumber())
                .totalPages(result.getTotalPages())
                .totalCount(result.getTotalElements())
                .pageSize(result.getSize())
                .hasNext(result.hasNext())
                .hasPrevious(result.hasPrevious())
                .isFirstPage(result.isFirst())
                .isLastPage(result.isLast())
                .build())
        .build();
  }
}
