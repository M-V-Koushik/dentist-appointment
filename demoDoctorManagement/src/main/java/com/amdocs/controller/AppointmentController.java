package com.amdocs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.entity.Appointment;
import com.amdocs.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/doctor/{doctorId}")
	public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		return appointmentService.getAppointmentsByDoctor(doctorId);
	}

	@GetMapping("/patient/{patientId}")
	public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
		return appointmentService.getAppointmentsByPatient(patientId);
	}

	@PostMapping("/book")
	public String bookAppointment(@RequestBody Appointment appointment) {

		if (appointmentService.isDoctorAvailable(appointment)) {
			appointmentService.bookAppointment(appointment);
			
			return "Appointment booked successfully!";
		} else {
			return "Doctor is not available at the specified time. Choose another time.";
		}
	}
	
	// API test 
	@GetMapping(value = "/test")
	public String shoppingDemo(){
		
		return "Success";	
	}
}
