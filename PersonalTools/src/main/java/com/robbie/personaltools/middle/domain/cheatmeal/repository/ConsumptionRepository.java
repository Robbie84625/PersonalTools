package com.robbie.personaltools.middle.domain.cheatmeal.repository;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Consumption;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsumptionRepository {
  List<Consumption> findAllByConsumedAtBetween(
      String customerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

  void saveConsumption(Consumption consumption);
}
