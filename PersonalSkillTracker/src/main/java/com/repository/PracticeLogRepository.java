package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillTracker.Skill;
import com.skillTracker.PracticeLog;

@Repository
public interface PracticeLogRepository extends JpaRepository<PracticeLog, Integer>
{
	List<PracticeLog> findBySkill(List<Skill> skills);
    List<PracticeLog> findBySkillAndSkill_Name(List<Skill> skills, String name);

}
