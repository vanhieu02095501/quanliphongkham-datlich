package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentStatusRepository extends JpaRepository<Status, Long> {
}