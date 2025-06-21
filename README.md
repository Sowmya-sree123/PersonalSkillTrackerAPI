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

# How to Test This API in Postman 

1. **Start** the Spring Boot app (Run as Spring Boot Application)  
2. Ensure **MySQL is running** and DB credentials are correct in `application.properties`  
3. **Register** a user → `POST /auth/register`  
4. **Login** → `POST /auth/login`, and copy the `JSESSIONID` from response cookies  
5. For `/skills` or `/logs`, add headers:
   - `Cookie`: `JSESSIONID=your_session_id_here`
6. In **Body tab**, select **Raw** and choose **JSON**
  
 Made with by Sowmya Rachuri – Java Full Stack Enthusiast
