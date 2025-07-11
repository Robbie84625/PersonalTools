package com.robbie.personaltools.font.controller.cheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealPresentation;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "作弊餐")
@RestController
@RequestMapping(value = "/cheatMeal")
@RequiredArgsConstructor
public class CheatMealController {
  private final CreateCheatMealPresentation createCheatMealPresentation;

  @Operation(summary = "新增作弊餐品項")
  @PostMapping("/createCheatMeal")
  public void createCheatMeal(@Valid @RequestBody CreateCheatMealRequest request) throws Exception {
    this.createCheatMealPresentation.execute(request);
  }
}
