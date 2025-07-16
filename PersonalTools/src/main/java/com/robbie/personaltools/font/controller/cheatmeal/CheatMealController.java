package com.robbie.personaltools.font.controller.cheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealItemRequest;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.GetCheatMealsPresentation;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmeals.model.GetCheatMealsResponse;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.UpdateCheatMealItemPresentation;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
  private final UpdateCheatMealItemPresentation updateCheatMealItemPresentation;
  private final GetCheatMealsPresentation getCheatMealsPresentation;

  @Operation(summary = "新增作弊餐品項")
  @PostMapping("/createCheatMealItem")
  public void createCheatMeal(@Valid @RequestBody CreateCheatMealItemRequest request)
      throws Exception {
    this.createCheatMealItemPresentation.execute(request);
  }

  @Operation(summary = "更新作弊餐品項")
  @PutMapping("/GetCheatMeals")
  public GetCheatMealsResponse getCheatMeals(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "20") Integer size) {
    return this.getCheatMealsPresentation.execute(keyword, category, page, size);
  }

  @Operation(summary = "更新作弊餐品項")
  @PutMapping("/updateCheatMealItem/{id}")
  public void updateCheatMeal(
      @PathVariable Long id, @RequestBody UpdateCheatMealItemRequest request) throws Exception {
    this.updateCheatMealItemPresentation.execute(id, request);
  }
}
