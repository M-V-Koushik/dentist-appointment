package com.amdocs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.entity.Doctor;
import com.amdocs.repo.AppointmentRepository;
import com.amdocs.repo.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
		super();
		this.doctorRepository = doctorRepository;
		this.appointmentRepository = appointmentRepository;
	}

	public List<Doctor> getAllDoctors() {

		return doctorRepository.findAll();

	}

	public Optional<Doctor> getDoctorById(Long id) {

		return doctorRepository.findById(id);
	}

	public void saveDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

}
