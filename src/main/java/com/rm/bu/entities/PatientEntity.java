package com.rm.bu.entities;

import com.rm.bu.common.entities.BaseEntity;
import com.rm.bu.enums.PatientStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private PatientStatus status;

}
