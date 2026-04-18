package com.rm.bu.repositories;

import com.rm.bu.entities.PatientEntity;
import com.rm.bu.enums.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface PatientRepository extends JpaRepository<PatientEntity,Long> {

    @Query("SELECT e FROM PatientEntity e WHERE e.id IN :ids")
    public List<PatientEntity> fetchPatientsBy(@Param("ids") Set<Long> patientsId);

    @Modifying
    @Transactional
    @Query("UPDATE PatientEntity p SET p.status = :status WHERE p.id IN :ids")
    public int updateStatusByIds(@Param("ids") Set<Long> patientsId, @Param("status") PatientStatus patientStatus);


}
