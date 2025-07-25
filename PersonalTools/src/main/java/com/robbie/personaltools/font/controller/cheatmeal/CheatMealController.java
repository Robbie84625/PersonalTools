package com.robbie.personaltools.font.controller.cheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealItemRequest;
import com.robbie.personaltools.font.api.cheatmeal.createconsumption.CreateConsumptionPresentation;
import com.robbie.personaltools.font.api.cheatmeal.createconsumption.model.CreateConsumptionRequest;
import com.robbie.personaltools.font.api.cheatmeal.deletecheatmealitem.DeleteCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.getbudget.GetBudgetPresentation;
import com.robbie.personaltools.font.api.cheatmeal.getbudget.model.GetBudgetResponse;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem.GetCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem.model.GetCheatMealItemResponse;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.GetCheatMealsPresentation;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.model.GetCheatMealsResponse;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.UpdateCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "作弊餐")
@RestController
@RequestMapping(value = "/cheatMeal")
@RequiredArgsConstructor
public class CheatMealController {
  private final CreateCheatMealItemPresentation createCheatMealItemPresentation;
  private final CreateConsumptionPresentation createConsumptionPresentation;
  private final GetCheatMealsPresentation getCheatMealsPresentation;
  private final GetCheatMealItemPresentation getCheatMealItemPresentation;
  private final GetBudgetPresentation getBudgetPresentation;
  private final UpdateCheatMealItemPresentation updateCheatMealItemPresentation;
  private final DeleteCheatMealItemPresentation deleteCheatMealItemPresentation;

  @Operation(summary = "新增作弊餐品項")
  @PostMapping("/createCheatMealItem")
  public void createCheatMeal(@Valid @RequestBody CreateCheatMealItemRequest request)
      throws Exception {
    this.createCheatMealItemPresentation.execute(request);
  }

  @Operation(summary = "新增消費紀錄")
  @PostMapping("/createConsumption")
  public void createConsumption(@Valid @RequestBody CreateConsumptionRequest request)
      throws Exception {
    this.createConsumptionPresentation.execute(request);
  }

  @Operation(summary = "取得作弊餐列表")
  @GetMapping("/getCheatMeals")
  public GetCheatMealsResponse getCheatMeals(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer size) {
    return this.getCheatMealsPresentation.execute(keyword, category, page, size);
  }

  @Operation(summary = "取得作弊餐品項內容")
  @GetMapping("/getCheatMealItem")
  public GetCheatMealItemResponse getCheatMealItem(
      @RequestParam(required = false) String cheatMealName,
      @RequestParam(required = false) Integer cheatMealPoint,
      @RequestParam(required = false) Integer currentRemainingBudget)
      throws Exception {
    return this.getCheatMealItemPresentation.execute(
        cheatMealName, cheatMealPoint, currentRemainingBudget);
  }

  @Operation(summary = "取得預算")
  @GetMapping("/getBudget")
  public GetBudgetResponse getBudget() throws Exception {
    return this.getBudgetPresentation.execute();
  }

  @Operation(summary = "更新作弊餐品項")
  @PutMapping("/updateCheatMealItem/{id}")
  public void updateCheatMeal(
      @PathVariable Long id, @RequestBody UpdateCheatMealItemRequest request) throws Exception {
    this.updateCheatMealItemPresentation.execute(id, request);
  }

  @Operation(summary = "刪除作弊餐品項")
  @DeleteMapping("/deleteCheatMealItem/{id}")
  public void deleteCheatMeal(@PathVariable Long id) throws Exception {
    this.deleteCheatMealItemPresentation.execute(id);
  }
}
