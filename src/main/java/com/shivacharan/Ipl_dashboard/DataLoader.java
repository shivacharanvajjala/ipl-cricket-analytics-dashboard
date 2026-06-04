package com.shivacharan.Ipl_dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public void run(String... args) throws Exception {
        if (matchRepository.count() > 0) {
            System.out.println("✅ Data already loaded!");
            return;
        }

        System.out.println("📊 Loading IPL data...");

        String csvFile = "src/main/resources/matches.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                String[] cols = line.split(",", -1);
                if (cols.length < 18) continue;
                try {
                    Match match = new Match();
                    match.setSeason(Integer.parseInt(cols[0].trim()));
                    match.setCity(cols[1].trim());
                    match.setDate(cols[2].trim());
                    match.setTeam1(cols[4].trim());
                    match.setTeam2(cols[5].trim());
                    match.setTossWinner(cols[6].trim());
                    match.setTossDecision(cols[7].trim());
                    match.setResult(cols[8].trim());
                    match.setWinner(cols[10].trim());
                    match.setPlayerOfMatch(cols[13].trim());
                    match.setVenue(cols[3].trim());
                    matchRepository.save(match);
                } catch (Exception e) {
                    // skip bad rows
                }
            }
        }
        System.out.println("✅ IPL data loaded successfully!");
    }
}