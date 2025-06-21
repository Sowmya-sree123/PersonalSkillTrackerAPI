package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.PracticeLogRepository;
import com.repository.SkillRepository;
import com.skillTracker.PracticeLog;
import com.skillTracker.Skill;

@Service
public class PracticeLogServiceImp implements PracticeLogService
{
	@Autowired
    private PracticeLogRepository logRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public boolean logPractice(PracticeLog log, int userId) {
        Skill skill = skillRepository.findById(log.getSkill().getId()).orElse(null);
        if (skill == null || skill.getUser().getId() != userId) {
            return false;
        }

        log.setSkill(skill);
        if (log.getDate() == null) {
            log.setDate(LocalDateTime.now());
        }
        logRepository.save(log);
        return true;
    }

    @Override
    public List<PracticeLog> getLogsBySkill(String skillName, int userId) {
        List<Skill> skills = skillRepository.findByUserId(userId);
        return logRepository.findBySkillAndSkill_Name(skills, skillName);
    }

    @Override
    public Map<String, Integer> getDashboard(int userId) {
        List<Skill> skills = skillRepository.findByUserId(userId);
        List<PracticeLog> logs = logRepository.findBySkill(skills);

        return logs.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getSkill().getName(),
                        Collectors.summingInt(PracticeLog::getDuration)
                ));
    }
}
