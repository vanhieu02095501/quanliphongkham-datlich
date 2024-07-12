package com.quanlyphongkhamvadatlich.service.dashboard.impl;

import com.quanlyphongkhamvadatlich.dto.dashboard.HistoryAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import com.quanlyphongkhamvadatlich.repository.HistoryAppointmentRepository;
import com.quanlyphongkhamvadatlich.service.dashboard.IHistoryAppointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryAppointmentService implements IHistoryAppointment {

    private final HistoryAppointmentRepository historyAppointmentRepository;
    @Override
    public List<HistoryAppointmentDTO> ListOfHistoryAppointmentByDates(Date startDate, Date endDate) {
        return historyAppointmentRepository.ListOfHistoryAppointmentByDates(startDate,endDate);
    }

    @Override
    public PatientRecord getPatientRecordById(Long id) {
        return historyAppointmentRepository.getPatientRecordById(id);
    }

}
