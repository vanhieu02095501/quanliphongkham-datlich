package com.quanlyphongkhamvadatlich.service.admin;

import java.util.List;

import com.quanlyphongkhamvadatlich.dto.Statistical;

public interface IStatisticalService {
    List<Statistical> getMonthlyRevenueByYear(int year);
}
