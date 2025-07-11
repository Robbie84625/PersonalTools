package com.robbie.personaltools.font.api.cheatmeal.createcheatmeal;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCheatMealFlow {
  public void execute(Command command) {}

  @Builder
  @Getter
  public static class Command {
    /** 作弊餐點名稱 */
    private String name;

    /** 作弊餐點等級 */
    private Integer level;

    /** 作弊餐點消耗額度 */
    private Integer point;

    /** 作弊餐點類別 */
    private String category;
  }
}
