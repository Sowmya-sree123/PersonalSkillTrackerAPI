#  Personal Skill Tracker API
A Spring Boot-based RESTful API to help users track their learning and practice progress across different skills like Java, Docker, Data Structures, etc.

# Features(with Endpoints)
✅ User Registration – POST /auth/register
✅ User Login – POST /auth/login
✅ Add New Skills – POST /skills
✅ View All Skills – GET /skills
✅ Track Skill Level (Beginner, Intermediate, Advanced)
✅ Log Practice Sessions – POST /logs
✅ View Logs by Skill – GET /logs?skill=SkillName
✅ Dashboard for Total Practice – GET /dashboard  

 # Technologies I Used
  Java 17
  Spring Boot
  Spring Security
  Spring Data JPA
  MySQL
  Postman (for testing)

 # Database Design

 👩‍💻 User Table
 id, name, email, password, createdAt

 📘 Skill Table
 id, name, level, targetHours, userId (foreign key)

 📒 PracticeLog Table
  id, skillId, date, duration, notes

 Made with by Sowmya Rachuri – Java Full Stack Enthusiast
