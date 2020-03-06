package com.personalproject.coronavirustracker.services;

import com.personalproject.coronavirustracker.models.ConfirmedStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidConfirmedCasesDataService {

    private static String CONFIRMED_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    private List<ConfirmedStats> allStats = new ArrayList<>();

    public List<ConfirmedStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchConfirmedCases() throws IOException, InterruptedException {
        List<ConfirmedStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CONFIRMED_VIRUS_DATA_URL)).build();
        //HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(CONFIRMED_VIRUS_DATA_URL)).build();
        HttpResponse<String> httpResponse= client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            ConfirmedStats locationStat = new ConfirmedStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setLat(record.get("Lat"));
            locationStat.setLong(record.get("Long"));
            locationStat.setLatestTotal(Integer.parseInt(record.get(record.size()-1)));
            locationStat.setDiffFromPreviousDay(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
            newStats.add(locationStat);

        }

        this.allStats=newStats;

    }


}
