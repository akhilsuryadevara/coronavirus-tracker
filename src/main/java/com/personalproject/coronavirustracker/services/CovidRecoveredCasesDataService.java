package com.personalproject.coronavirustracker.services;

import com.personalproject.coronavirustracker.models.RecoveredStats;
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
public class CovidRecoveredCasesDataService {
    private static String RECOVERED_VIRUS_DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";
    private List<RecoveredStats> allRecoveredStats = new ArrayList<>();

    public List<RecoveredStats> getAllRecoveredStats() {
        return allRecoveredStats;
    }

    @PostConstruct
    @Scheduled(cron="* * 1 * * *")
    public void recoveredCases() throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(RECOVERED_VIRUS_DATA_URI)).build();

    //HttpResponse<String> = client.send(httpRequest,HttpResponse.BodyHandlers.ofString());
    HttpResponse<String> httpResponse= client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    StringReader csvBodyReader = new StringReader(httpResponse.body());
    //Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    List<RecoveredStats> newStats = new ArrayList<>();
     for (CSVRecord record : records) {
        RecoveredStats recoveryStat = new RecoveredStats();
        recoveryStat.setState(record.get("Province/State"));
        recoveryStat.setCountry(record.get("Country/Region"));
        recoveryStat.setLat(record.get("Lat"));
        recoveryStat.setLong(record.get("Long"));
        recoveryStat.setTotalRecoveredCases(Integer.parseInt(record.get(record.size()-1)));
        recoveryStat.setTotalRecoveredCasesSincePreviousDay(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
        newStats.add(recoveryStat);
    }
     this.allRecoveredStats = newStats;



}
}
