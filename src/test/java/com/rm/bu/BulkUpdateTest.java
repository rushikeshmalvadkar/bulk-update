package com.rm.bu;

import com.github.javafaker.Faker;
import com.rm.bu.common.AbstractUnitTest;
import com.rm.bu.entities.PatientEntity;
import com.rm.bu.enums.PatientStatus;
import com.rm.bu.models.PatientStausChangeRequest;
import com.rm.bu.repositories.PatientRepository;
import com.rm.bu.services.BulkUpdatePatientService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class BulkUpdateTest extends AbstractUnitTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BulkUpdatePatientService bulkUpdatePatientService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void insert_fake_patients_data() {
        patientRepository.deleteAll();
        Faker faker = new Faker();
        List<PatientEntity> patients = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            PatientEntity p = new PatientEntity();
            p.setName(faker.name().fullName());
            p.setStatus(PatientStatus.ADMITTED);
            entityManager.persist(p);
        }
    }

    @Test
    void should_update_bulk_patients_status() {
        PatientStausChangeRequest patientStausChangeRequest = new PatientStausChangeRequest();
        patientStausChangeRequest.setStatus(PatientStatus.DISCHARGED);
        for (long i = 1L; i < 10000; i++) {
            patientStausChangeRequest.addId(i);
        }
        bulkUpdatePatientService.bulkUpdatePatientStatus(patientStausChangeRequest);
    }

    @Test
    void should_update_bulk_patients_status_by_custom_update_query() {
        PatientStausChangeRequest patientStausChangeRequest = new PatientStausChangeRequest();
        patientStausChangeRequest.setStatus(PatientStatus.DISCHARGED);
        for (long i = 1L; i < 10000; i++) {
            patientStausChangeRequest.addId(i);
        }
        bulkUpdatePatientService.bulkUpdateByCustomQuery(patientStausChangeRequest);
    }
}
