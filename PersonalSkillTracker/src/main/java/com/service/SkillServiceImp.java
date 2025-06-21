package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.SkillRepository;
import com.repository.UserRepository;
import com.skillTracker.Skill;
import com.skillTracker.User;

@Service
public class SkillServiceImp implements SkillService
{
	@Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addSkill(Skill skill, int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            skill.setUser(userOptional.get());
            skillRepository.save(skill);
            return true;
        }
        return false;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public boolean updateSkill(int id, Skill updatedSkill, int userId) {
        Optional<Skill> existingSkillOpt = skillRepository.findById(id);

        if (existingSkillOpt.isPresent()) {
            Skill existingSkill = existingSkillOpt.get();
            if (existingSkill.getUser().getId() == userId) {
                existingSkill.setLevel(updatedSkill.getLevel());
                existingSkill.setTargetHours(updatedSkill.getTargetHours());
                skillRepository.save(existingSkill);
                return true;
            }
        }
        return false;
    }
}
