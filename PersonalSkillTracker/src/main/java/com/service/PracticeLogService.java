package com.service;

import java.util.List;
import java.util.Map;

import com.skillTracker.PracticeLog;

public interface PracticeLogService 
{
    boolean logPractice(PracticeLog log, int userId);
    List<PracticeLog> getLogsBySkill(String skillName, int userId);
    Map<String, Integer> getDashboard(int userId);
}
