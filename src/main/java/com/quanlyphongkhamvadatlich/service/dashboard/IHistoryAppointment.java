package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IHistoryAppointment {
    public List<HistoryAppointmentDTO> ListOfHistoryAppointmentByDates(Date startDate, Date endDate);
    public PatientRecord getPatientRecordById(Long id);
}
