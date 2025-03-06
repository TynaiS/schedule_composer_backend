# Schedule composer backend
This is a backend part of the **Schedule composer**, app that allows to create custom schedules, view and publish them.

## Pipeline
[![Deploy App](https://github.com/TynaiS/schedule_composer_backend/actions/workflows/deploy.yml/badge.svg?branch=main)](https://github.com/TynaiS/schedule_composer_backend/actions/workflows/deploy.yml)

## How to run
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
  5) You database is set up, you can now run the application!



Run the application (ScheduleComposerApplication.java file) and go to http://localhost:8080/swagger-ui/index.html to see API documentation
