package org.benchmarks.dao;

import org.benchmarks.model.DailyJobStatusEntity;
import org.springframework.data.repository.CrudRepository;

public interface DailyJobStatusRepository extends CrudRepository<DailyJobStatusEntity, Long> {

}
