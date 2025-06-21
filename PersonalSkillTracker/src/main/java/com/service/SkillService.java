package com.service;

import java.util.List;

import com.skillTracker.Skill;
import com.skillTracker.User;

public interface SkillService 
{
	boolean addSkill(Skill skill, int userId);
    List<Skill> getAllSkills();
    boolean updateSkill(int id, Skill updatedSkill, int userId);

}
