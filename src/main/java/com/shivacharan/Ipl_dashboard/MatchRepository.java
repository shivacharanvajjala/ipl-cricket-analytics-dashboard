package com.shivacharan.Ipl_dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findBySeason(int season);
    
    List<Match> findByTeam1OrTeam2(String team1, String team2);
    
    @Query("SELECT m.winner, COUNT(m) as wins FROM Match m WHERE m.winner != '' GROUP BY m.winner ORDER BY wins DESC")
    List<Object[]> findTeamWinCount();
    
    @Query("SELECT m.season, COUNT(m) as matches FROM Match m GROUP BY m.season ORDER BY m.season")
    List<Object[]> findMatchesPerSeason();
    
    @Query("SELECT m.playerOfMatch, COUNT(m) as awards FROM Match m WHERE m.playerOfMatch != '' GROUP BY m.playerOfMatch ORDER BY awards DESC")
    List<Object[]> findTopPlayerOfMatch();
    
    @Query("SELECT m.tossDecision, COUNT(m) as count FROM Match m GROUP BY m.tossDecision")
    List<Object[]> findTossDecisionStats();
}