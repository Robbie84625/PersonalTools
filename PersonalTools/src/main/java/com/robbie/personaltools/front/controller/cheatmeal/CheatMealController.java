package com.robbie.personaltools.front.controller.cheatmeal;

import com.robbie.personaltools.front.api.cheatmeal.createcheatmeal.CreateCheatMealItemPresentation;
import com.robbie.personaltools.front.api.cheatmeal.createcheatmeal.model.CreateCheatMealItemRequest;
import com.robbie.personaltools.front.api.cheatmeal.createrecord.CreateRecordPresentation;
import com.robbie.personaltools.front.api.cheatmeal.createrecord.model.CreateRecordRequest;
import com.robbie.personaltools.front.api.cheatmeal.deletecheatmealitem.DeleteCheatMealItemPresentation;
import com.robbie.personaltools.front.api.cheatmeal.getSettingData.GetSettingDataPresentation;
import com.robbie.personaltools.front.api.cheatmeal.getSettingData.model.GetSettingDataResponse;
import com.robbie.personaltools.front.api.cheatmeal.getbudget.GetBudgetPresentation;
import com.robbie.personaltools.front.api.cheatmeal.getbudget.model.GetBudgetResponse;
import com.robbie.personaltools.front.api.cheatmeal.getcheatmeals.GetCheatMealsPresentation;
import com.robbie.personaltools.front.api.cheatmeal.getcheatmeals.model.GetCheatMealsResponse;
import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.GetConsumptionRecordPresentation;
import com.robbie.personaltools.front.api.cheatmeal.getconsumptionrecord.model.GetConsumptionRecordResponse;
import com.robbie.personaltools.front.api.cheatmeal.updatebudget.UpdateUserBudgetPresentation;
import com.robbie.personaltools.front.api.cheatmeal.updatebudget.model.UpdateUserBudgetRequest;
import com.robbie.personaltools.front.api.cheatmeal.updatecheatmeal.UpdateCheatMealItemPresentation;
import com.robbie.personaltools.front.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealItemRequest;
import com.robbie.personaltools.front.api.cheatmeal.updateusername.UpdateUserNamePresentation;
import com.robbie.personaltools.front.api.cheatmeal.updateusername.model.UpdateUserNameRequest;
import com.robbie.personaltools.infra.exception.ValidException;
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
  private final CreateRecordPresentation createRecordPresentation;
  private final GetCheatMealsPresentation getCheatMealsPresentation;
  private final GetBudgetPresentation getBudgetPresentation;
  private final GetConsumptionRecordPresentation getConsumptionRecordPresentation;
  private final GetSettingDataPresentation getSettingDataPresentation;
  private final UpdateCheatMealItemPresentation updateCheatMealItemPresentation;
  private final UpdateUserNamePresentation updateUserNamePresentation;
  private final UpdateUserBudgetPresentation updateUserBudgetPresentation;
  private final DeleteCheatMealItemPresentation deleteCheatMealItemPresentation;

  @Operation(summary = "新增作弊餐品項")
  @PostMapping("/createCheatMealItem")
  public void createCheatMeal(@Valid @RequestBody CreateCheatMealItemRequest request)
      throws Exception {
    this.createCheatMealItemPresentation.execute(request);
  }

  @Operation(summary = "新增消費紀錄")
  @PostMapping("/createRecord")
  public void createRecord(@Valid @RequestBody CreateRecordRequest request) throws Exception {
    this.createRecordPresentation.execute(request);
  }

  @Operation(summary = "取得作弊餐列表")
  @GetMapping("/getCheatMeals")
  public GetCheatMealsResponse getCheatMeals(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer size)
      throws ValidException {
    return this.getCheatMealsPresentation.execute(keyword, category, page, size);
  }

  @Operation(summary = "取得預算")
  @GetMapping("/getBudget")
  public GetBudgetResponse getBudget() throws Exception {
    return this.getBudgetPresentation.execute();
  }

  public GetConsumptionRecordResponse getConsumptionRecord(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer size)
      throws Exception {
    return this.getConsumptionRecordPresentation.execute(page, size);
  }

  @Operation(summary = "取得設定頁面")
  @GetMapping("/getSettingData")
  public GetSettingDataResponse getSettingData() throws Exception {
    return this.getSettingDataPresentation.execute();
  }

  @Operation(summary = "更新作弊餐品項")
  @PutMapping("/updateCheatMealItem/{id}")
  public void updateCheatMeal(
      @PathVariable Long id, @RequestBody UpdateCheatMealItemRequest request) throws Exception {
    this.updateCheatMealItemPresentation.execute(id, request);
  }

  @Operation(summary = "更新使用者名稱")
  @PutMapping("/updateUserName")
  public void updateUserName(@RequestBody @Valid UpdateUserNameRequest request) throws Exception {
    this.updateUserNamePresentation.execute(request);
  }

  @Operation(summary = "更新使用者預算")
  @PutMapping("/updateUserBudget")
  public void updateUserBudget(@RequestBody @Valid UpdateUserBudgetRequest request)
      throws Exception {
    this.updateUserBudgetPresentation.execute(request);
  }

  @Operation(summary = "刪除作弊餐品項")
  @DeleteMapping("/deleteCheatMealItem/{id}")
  public void deleteCheatMeal(@PathVariable Long id) throws Exception {
    this.deleteCheatMealItemPresentation.execute(id);
  }
}
