package com.amdocs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.entity.Patient;
import com.amdocs.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/getAll")
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
		Optional<Patient> patient = patientService.getPatientById(id);
		return patient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/addPatient")
	public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return ResponseEntity.ok("Patient created successfully");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
		if (patientService.getPatientById(id).isPresent()) {
			updatedPatient.setId(id);
			patientService.savePatient(updatedPatient);
			return ResponseEntity.ok("Patient updated successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable Long id) {
		if (patientService.getPatientById(id).isPresent()) {
			patientService.deletePatient(id);
			return ResponseEntity.ok("Patient deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
