package com.rm.bu.models;

import com.rm.bu.enums.PatientStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PatientStausChangeRequest {
    private Set<Long> ids = new HashSet<>();
    private PatientStatus status;

    public void addId(Long id) {
        this.ids.add(id);
    }
}
