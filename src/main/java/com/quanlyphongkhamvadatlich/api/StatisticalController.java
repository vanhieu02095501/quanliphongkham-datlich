package com.quanlyphongkhamvadatlich.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanlyphongkhamvadatlich.dto.Statistical;
import com.quanlyphongkhamvadatlich.service.admin.impl.StatisticalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/revenue/{year}")
    public ResponseEntity<List<Statistical>> getMonthlyRevenueByYear(@PathVariable(value = "year") int year) {
        return ResponseEntity.ok().body(statisticalService.getMonthlyRevenueByYear(year));
    }
}
