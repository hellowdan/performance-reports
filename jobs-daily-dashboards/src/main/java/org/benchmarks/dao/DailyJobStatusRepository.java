package org.benchmarks.dao;

import org.benchmarks.model.DailyJobStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyJobStatusRepository extends CrudRepository<DailyJobStatusEntity, Long> {

}
