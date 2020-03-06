package com.personalproject.coronavirustracker.controllers;

import com.personalproject.coronavirustracker.models.ConfirmedStats;
import com.personalproject.coronavirustracker.services.CovidConfirmedCasesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConfirmedCasesController {

    @Autowired
    CovidConfirmedCasesDataService covidConfirmedCasesDataService;

    @GetMapping("/")
    public String confirmedCases(Model model){
        List<ConfirmedStats> allStats = covidConfirmedCasesDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat->stat.getLatestTotal()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat->stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("confirmedStats", allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);


        return "confirmedcases";
    }

}

