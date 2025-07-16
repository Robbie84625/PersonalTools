package com.robbie.personaltools.font.controller.cheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealPresentation;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealRequest;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.UpdateCheatMealPresentation;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "作弊餐")
@RestController
@RequestMapping(value = "/cheatMeal")
@RequiredArgsConstructor
public class CheatMealController {
  private final CreateCheatMealPresentation createCheatMealPresentation;
  private final UpdateCheatMealPresentation updateCheatMealPresentation;

  @Operation(summary = "新增作弊餐品項")
  @PostMapping("/createCheatMealItem")
  public void createCheatMeal(@Valid @RequestBody CreateCheatMealRequest request) throws Exception {
    this.createCheatMealPresentation.execute(request);
  }

  @Operation(summary = "更新作弊餐品項")
  @PutMapping("/updateCheatMealItem/{id}")
  public void updateCheatMeal(@PathVariable Long id, @RequestBody UpdateCheatMealRequest request)
      throws Exception {
    this.updateCheatMealPresentation.execute(id, request);
  }
}
