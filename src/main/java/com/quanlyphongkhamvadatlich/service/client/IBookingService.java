package com.quanlyphongkhamvadatlich.service.client;

import com.quanlyphongkhamvadatlich.dto.client.AppointmentDTO;
import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.User;
import jakarta.mail.MessagingException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingService {
    public Optional<Appointment> getAppointmentById(Long id);
    public List<Appointment> getAppointmentByDateAndShift(Date appointmentDate, String appointmentShift);
    public List<DisableAppointmentDTO> getAllDisableAppointments();
    public Appointment bookAppointment(AppointmentDTO booking, Optional<Patient> patientOptional, int statusId, User user) throws Exception;
    public void sendAppointmentInfo(String receiver, Integer orderNumber, Long patientId,String name, String phone, Date appointmentDate, String appointmentShift) throws MessagingException;
}
