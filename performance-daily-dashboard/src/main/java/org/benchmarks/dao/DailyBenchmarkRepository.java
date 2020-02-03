package org.benchmarks.dao;

import org.benchmarks.model.DailyBenchmarkEntity;
import org.springframework.data.repository.CrudRepository;

public interface DailyBenchmarkRepository extends CrudRepository<DailyBenchmarkEntity, Long> {

}
