package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.ConsumptionDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Consumption;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.ConsumptionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumptionPersistence implements ConsumptionRepository {

  private final ConsumptionDao consumptionDao;

  @Override
  public List<Consumption> findAllByConsumedAtBetween(
      String customerId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
    return this.consumptionDao.findByCustomerIdAndDateRange(customerId, startDateTime, endDateTime);
  }

  @Override
  public void saveConsumption(Consumption consumption) {
    this.consumptionDao.save(consumption);
  }
}
