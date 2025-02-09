To setup database: 
  1) Create your PostgreSQL database locally.
  2) Change these lines
     "
         url: jdbc:postgresql://localhost:5432/schedule_composer
         username: postgres
         password: postgres
     "
     in /src/main/resources/application.yml to your database path, username and password.
  4) Go to /sql/database_setup_sql, copy the code and run in your database sql file.
  5) You database is set up, you can run the application!



Run the application (ScheduleComposerApplication.java file) and go to http://localhost:8080/swagger-ui/index.html to see API documentation
