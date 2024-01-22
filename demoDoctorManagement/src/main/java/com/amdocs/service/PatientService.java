package com.amdocs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.entity.Patient;
import com.amdocs.repo.AppointmentRepository;
import com.amdocs.repo.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Optional<Patient> getPatientById(Long id) {
		return patientRepository.findById(id);
	}

	public void savePatient(Patient patient) {
		
		patientRepository.save(patient);
	}

	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}

}
