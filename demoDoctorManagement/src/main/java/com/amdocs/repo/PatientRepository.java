package com.amdocs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
