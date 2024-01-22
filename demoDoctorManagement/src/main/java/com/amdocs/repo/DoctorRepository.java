package com.amdocs.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByIdAndAppointmentsAppointmentTime(Long doctorId, LocalDateTime appointmentTime);
}
