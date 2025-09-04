package com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord;

import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.GetConsumptionRecordFlow.Command;
import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.GetConsumptionRecordFlow.Result;
import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.model.GetConsumptionRecordResponse;
import com.robbie.personaltools.infra.exception.ValidException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("getConsumptionRecordPresentation")
public class GetConsumptionRecordPresentation {
  private final GetConsumptionRecordFlow getConsumptionRecordFlow;

  public GetConsumptionRecordResponse execute(Integer page, Integer size) throws ValidException {
    Page<Result> result =
        this.getConsumptionRecordFlow.execute(Command.builder().page(page).size(size).build());

    return GetConsumptionRecordResponse.builder()
        .consumptionRecords(
            result.getContent().stream()
                .map(
                    consumptionRecord ->
                        GetConsumptionRecordResponse.ConsumptionRecord.builder()
                            .startDate(consumptionRecord.getStartDate())
                            .endDate(consumptionRecord.getEndDate())
                            .budget(consumptionRecord.getBudget())
                            .totalConsumption(consumptionRecord.getTotalConsumption())
                            .status(consumptionRecord.isStatus())
                            .cheatMealList(
                                consumptionRecord.getCheatMealList().stream()
                                    .map(
                                        cheatMeal ->
                                            GetConsumptionRecordResponse.CheatMeal.builder()
                                                .mealName(cheatMeal.getMealName())
                                                .mealPoint(cheatMeal.getMealPoint())
                                                .build())
                                    .collect(Collectors.toList()))
                            .build())
                .collect(Collectors.toList()))
        .pagination(
            GetConsumptionRecordResponse.PaginationInfo.builder()
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
