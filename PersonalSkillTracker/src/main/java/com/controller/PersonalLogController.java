package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.PracticeLogService;
import com.skillTracker.PracticeLog;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/logs")
public class PersonalLogController 
{
	@Autowired
    private PracticeLogService practiceLogService;

    //log practice session
    @PostMapping
    public ResponseEntity<String> logPractice(@RequestBody PracticeLog log, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first.");
        }

        boolean saved = practiceLogService.logPractice(log, userId);
        if (saved) {
            return ResponseEntity.ok("Practice log saved.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Skill not found");
        }
    }

    //list logs by skill
    @GetMapping
    public ResponseEntity<List<PracticeLog>> getLogsBySkillName(
            @RequestParam("skill") String skillName, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PracticeLog> logs = practiceLogService.getLogsBySkill(skillName, userId);
        return ResponseEntity.ok(logs);
    }

    // total time spent per skill
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(practiceLogService.getDashboard(userId));
    }

}
