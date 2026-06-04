package com.shivacharan.Ipl_dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    // Get all matches
    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // Get matches by season
    @GetMapping("/matches/season/{season}")
    public List<Match> getMatchesBySeason(@PathVariable int season) {
        return matchRepository.findBySeason(season);
    }

    // Get matches by team
    @GetMapping("/matches/team/{team}")
    public List<Match> getMatchesByTeam(@PathVariable String team) {
        return matchRepository.findByTeam1OrTeam2(team, team);
    }

    // Get team win counts
    @GetMapping("/stats/teamwins")
    public List<Map<String, Object>> getTeamWins() {
        List<Object[]> results = matchRepository.findTeamWinCount();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("team", row[0]);
            map.put("wins", row[1]);
            response.add(map);
        }
        return response;
    }

    // Get matches per season
    @GetMapping("/stats/seasons")
    public List<Map<String, Object>> getMatchesPerSeason() {
        List<Object[]> results = matchRepository.findMatchesPerSeason();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("season", row[0]);
            map.put("matches", row[1]);
            response.add(map);
        }
        return response;
    }

    // Get top player of match
    @GetMapping("/stats/topplayers")
    public List<Map<String, Object>> getTopPlayers() {
        List<Object[]> results = matchRepository.findTopPlayerOfMatch();
        List<Map<String, Object>> response = new ArrayList<>();
        int limit = Math.min(10, results.size());
        for (int i = 0; i < limit; i++) {
            Object[] row = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("player", row[0]);
            map.put("awards", row[1]);
            response.add(map);
        }
        return response;
    }

    // Get toss decision stats
    @GetMapping("/stats/toss")
    public List<Map<String, Object>> getTossStats() {
        List<Object[]> results = matchRepository.findTossDecisionStats();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("decision", row[0]);
            map.put("count", row[1]);
            response.add(map);
        }
        return response;
    }

    // Get total stats
    @GetMapping("/stats/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalMatches", matchRepository.count());
        summary.put("totalSeasons", matchRepository.findMatchesPerSeason().size());
        return summary;
    }
}