package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillTracker.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>
{
	List<Skill> findByUserId(int userId);
}
