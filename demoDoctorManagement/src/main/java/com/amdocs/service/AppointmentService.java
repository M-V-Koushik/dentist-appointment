package com.amdocs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdocs.entity.Appointment;
import com.amdocs.entity.Doctor;
import com.amdocs.entity.Patient;
import com.amdocs.repo.AppointmentRepository;
import com.amdocs.repo.DoctorRepository;
import com.amdocs.repo.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
			PatientRepository patientRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	public void bookAppointment(Appointment appointment) {
		
		appointmentRepository.save(appointment);

	}

	public boolean isDoctorAvailable(Appointment appointment) {
		
		LocalDateTime appointmentTime = appointment.getAppointmentTime();

		List<Appointment> existingAppointments = appointmentRepository.findByDoctorAndAppointmentTimeAfter(appointmentTime);
		System.out.println(existingAppointments);
		return existingAppointments.isEmpty();
	}
	
	public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		return optionalDoctor.map(appointmentRepository::findByDoctor).orElse(List.of());
	}

	public List<Appointment> getAppointmentsByPatient(Long patientId) {
		Optional<Patient> optionalPatient = patientRepository.findById(patientId);
		return optionalPatient.map(appointmentRepository::findByPatient).orElse(List.of());
	}

}
