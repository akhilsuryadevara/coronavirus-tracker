package com.personalproject.coronavirustracker.controllers;

import com.personalproject.coronavirustracker.models.RecoveredStats;
import com.personalproject.coronavirustracker.services.CovidRecoveredCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecoveredCasesController {
    @Autowired
    CovidRecoveredCasesDataService covidRecoveredCasesDataService;

    @GetMapping("/recoveredcases")
    public String recoveredCases(Model model){
        List<RecoveredStats> recoveredStats = covidRecoveredCasesDataService.getAllRecoveredStats();
        int totalRecoveredCases = recoveredStats.stream().mapToInt(stat->stat.getTotalRecoveredCases()).sum();
        model.addAttribute("recoveredStats",recoveredStats);
        model.addAttribute("totalRecoveredCases",totalRecoveredCases);

        return "recoveredcases";
    }
}
