SHORT VERSION - Steps 1-8 only need to be completed once.
1. Clone both server and client repositories.
2. Import both into your preferred IDE(s).
3. Ensure dependencies are properly installed and integrated with your IDE.
4. Navigate to revature-server/src/main/resources and open application.properties
5. Change "spring.jpa.hibernate.ddl-auto=validate" to "spring.jpa.hibernate.ddl-auto=create" without quotes.
6. Run the project as a Spring Boot app.
7. Go back to application.properties and change "spring.jpa.hibernate.ddl-auto=create" back to "spring.jpa.hibernate.ddl-auto=validate" without quotes.
8. Run 'npm install' (without quotes) on the client.
9. Both projects are now ready to be run.

-----

LONG VERSION - Steps 1-17 only need to be completed once.
1. Create a new folder to contain the project.
2. Clone both the client and server repositories to this directory.
3. Open your preferred Spring IDE (this guide assumes you are using Spring Tool Suite).
4. Open the base directory as your workspace or import it into your IDE's workspace.
5. Import a the server folder as an existing Maven project.
6. Ensure all dependencies are properly installed.
7. If your IDE gives you errors, ensure that you have Lombok properly installed. If not, move to step 8.
- 7a. Find and run lombok-[VERSION].jar file (I found it at C:\Users\Me\.m2\repository\org\projectlombok\lombok\[VERSION]) and follow the instructions
- 7b. Open your IDE and rebuild the project with Maven. (With Spring Tool Suite, right click project->Maven->Update Project)
8. Navigate to src/main/resources and open application.properties
9. Find the line that has "spring.jpa.hibernate.ddl-auto=validate" and change it to "spring.jpa.hibernate.ddl-auto=create" without quotes.
10. Launch the project as a Spring Boot App.
11. Halt the program and go back to application.properties.
12. Change "spring.jpa.hibernate.ddl-auto=create" back to "spring.jpa.hibernate.ddl-auto=validate" without quotes.
13. Open your preferred Angular IDE (this guide assumes you are using VS Code).
14. Right click workspace and import a new folder, choosing the revatrade-client folder.
15. Select yes if asked whether you trust the authors.
16. Right click the new folder in your workspace and select "Open in integrated terminal"
17. Run the command 'npm install' without quotes.
18. To launch the project from here, run the server as a Spring Boot app then run the command 'ng serve -o' in the integrated terminal.