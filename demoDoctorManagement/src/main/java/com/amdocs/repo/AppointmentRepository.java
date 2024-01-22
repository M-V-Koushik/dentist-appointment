package com.amdocs.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amdocs.entity.Appointment;
import com.amdocs.entity.Doctor;
import com.amdocs.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByDoctor(Doctor doctor);

	List<Appointment> findByPatient(Patient patient);

	@Query(value = "SELECT * FROM doctordb.appointment where appointment_time=?1", nativeQuery = true)
	List<Appointment> findByDoctorAndAppointmentTimeAfter(@Param(value = "appointment_time") LocalDateTime appointmentTime);
	 
}
