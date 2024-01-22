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

import com.amdocs.entity.Doctor;
import com.amdocs.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/getAll")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		Optional<Doctor> doctor = doctorService.getDoctorById(id);
		return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/addDoc")
	public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return ResponseEntity.ok("Doctor created successfully");
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
		if (doctorService.getDoctorById(id).isPresent()) {
			updatedDoctor.setId(id);
			doctorService.saveDoctor(updatedDoctor);
			return ResponseEntity.ok("Doctor updated successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
		if (doctorService.getDoctorById(id).isPresent()) {
			doctorService.deleteDoctor(id);
			return ResponseEntity.ok("Doctor deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
