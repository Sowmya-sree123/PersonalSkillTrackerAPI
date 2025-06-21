package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.SkillService;
import com.skillTracker.Skill;
import com.skillTracker.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/skills")
public class SkillController 
{
	 @Autowired
	    private SkillService skillService;

	    //adding the skills
	    @PostMapping
	    public ResponseEntity<String> addSkill(@RequestBody Skill skill, HttpSession session) {
	        Object userIdobj= session.getAttribute("userId");

	        if (userIdobj == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first.");
	        }
	        int userId=(int) userIdobj;

	        boolean saved = skillService.addSkill(skill, userId);
	        if (saved) {
	            return ResponseEntity.ok("Skill added successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while adding the skill");
	        }
	    }

	    //get the skills
	    @GetMapping
	    public List<Skill> getAllSkills() {
	        return skillService.getAllSkills();
	    }

	    //updating skill level or target hours
	    @PatchMapping("/{id}")
	    public ResponseEntity<String> updateSkill(@PathVariable int id,
	                                              @RequestBody Skill updatedSkill,
	                                              HttpSession session) {
	        Integer userId = (Integer) session.getAttribute("userId");

	        if (userId == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first.");
	        }

	        boolean updated = skillService.updateSkill(id, updatedSkill, userId);
	        if (updated) {
	            return ResponseEntity.ok("Skill updated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found");
	        }
	    }
}
