package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    public Optional<Status> findById(Integer id);

}
