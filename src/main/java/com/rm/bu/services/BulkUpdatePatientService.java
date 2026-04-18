package com.rm.bu.services;

import com.rm.bu.entities.PatientEntity;
import com.rm.bu.models.PatientStausChangeRequest;
import com.rm.bu.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BulkUpdatePatientService {

    private final PatientRepository patientRepository;

    public void bulkUpdatePatientStatus(PatientStausChangeRequest patientStausChangeRequests) {

        long start = System.currentTimeMillis();
        List<PatientEntity> patientEntities = patientRepository.fetchPatientsBy(patientStausChangeRequests.getIds());
        List<PatientEntity> updatedPatients = new ArrayList<>();
        for (PatientEntity patient : patientEntities) {
            patient.setStatus(patientStausChangeRequests.getStatus());
            updatedPatients.add(patient);
        }
        patientRepository.saveAll(updatedPatients);
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " ms");

    }

    public void bulkUpdateByCustomQuery(PatientStausChangeRequest patientStausChangeRequest) {
        int rowsCount = patientRepository.updateStatusByIds(patientStausChangeRequest.getIds(), patientStausChangeRequest.getStatus());
        System.out.println(rowsCount);
    }
}
