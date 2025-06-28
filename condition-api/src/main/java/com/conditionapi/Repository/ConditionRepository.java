package com.conditionapi.Repository;
import com.conditionapi.Entity.ConditionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {
    List<ConditionEntity> findByPatientId(String patientId);
}