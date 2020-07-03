package org.benchmarks.dao;

import org.benchmarks.model.JobsStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsStatusRepository extends CrudRepository<JobsStatusEntity, Long> {

}
