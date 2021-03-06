# ExplosivePomegranate


## Project Management
### Project purpose, project members
The project team consisted of 4 members: Salvatore Trupia, Clelia Meneghin, Sonja Nussbaumer and Rebecca Beutling.<br />
This project deemed to deploy a website based on Java, HTML, CSS, JavaScript and a mySQL database. The backend is supported by Springboot, the source code, artefacts and further documentation is available on GitLab: https://gitlab.fhnw.ch/clelia.meneghin/explosivepomegranate/. <br />
The basic idea is a library, where FHNW students can borrow books or their studies and librarians administer the books in their admin role. To see the final result, you can refer to: https://library-fhnw.herokuapp.com/ <br />
The knowledge preservation was ensured, by documenting major tipps and tricks in the Wiki: https://gitlab.fhnw.ch/clelia.meneghin/explosivepomegranate/-/wikis/home

### Time Management
The time management was based on the lecture contents and given fixe dates, like the submission on 10th of January 2022.
![Class Diagram](documentation-resources/Gantt.png?raw=true "Title")

### Use Case diagram

The initial Use case diagram shows the basis for the whole set-up of the website.
During the run of the project, some requirements have changed and may not be applicable anymore. 

[Edit UML in the Lucid App](https://lucid.app/lucidchart/4c7d5f12-b21d-4bff-988e-84bd686c2cd3/edit?viewport_loc=108%2C-247%2C1111%2C777%2C0_0&invitationId=inv_3a662d3c-c919-4256-8e0d-2f78abadef9b)

![UML user stories](documentation-resources/UML-usecases.png?raw=true "Title")
 
### User Stories

The Use cases were separated between the MVP, which has been implemented and the Future improvement steps. The latter is not in place, but are seen as next steps, in case such a website would be taken further.

#### MVP - Minimal Viable Product

1. UC1: As a student of the FHNW, I want to use the web app on my different devices (i.e mobile devices, laptops, and desktop
   computers) so that I can access the web app everywhere.
2. UC2: As a student of the FHNW, I want to clear and simple UI so that I can navigate quickly and smoothly
3. UC3: As a student of the FHNW, I want to create an account so that I can access the web app.
4. UC4: As a student of the FHNW, I want to log-in so that I can authenticate myself.
5. UC5: As a student of the FHNW, I want to search for books within the library so that I can see if the library has them
6. UC6: As a student of the FHNW, I want to be able to see whether a book is currently available so that I can borrow it
7. UC7: As a student of the FHNW, I want to reserve/borrow books so that I can pick them up at the physical library
8. UC8: As a student of the FHNW, I want to see which books I'm currently borrowing and the history of books, I already borrowed.
9. UC9: As a student of the FHNW, I want to stay logged in the webinterface when I close the browser window.
10. UC10: As a Librarian, I want to be able to add new books into the system.
11. UC11: As a Librarian, I want to have an overview over books that are currently borrowed.
12. UC12: As a Librarian, I want to see the dates of borrowed books
13. UC18: As a student of the FHNW, I want to add a comment about a book condition for everyone to see so that I do not get fined for mistreating the book when the librarian notices something wrong with the book.
14. UC20: As a Librarian, I can mark books as returned.

#### Future improvement steps
15. UC13: As a Librarian, I want to add students accounts so that the students of the FHNW have access to the library.
16. UC14: As a students of the FHNW, I want a shopping card like experience for books I want to lend so that I keep the overview over how many books I added already.
17. UC15: As a Libriarian, I want to add a image of the book cover to the book information.
18. UC16: As a Libraran, I want a reporting functionality about all the borrowed books so that I can easily search for return dates for specific items and overdo books
19. UC17: As a Librarian, I want that the "return reminder" for borrowed books is send automatically so that I save time and can focus on keeping the library up to date.
20. UC19: As a Libriarian, I can delete book records.

### Class diagram
[Edit class diagram in lucid](https://lucid.app/lucidchart/invitations/accept/inv_dc1b4624-c4b7-4b75-be56-0053b5493778?viewport_loc=-8%2C1248%2C2048%2C1085%2C0_0)
![Class Diagram](documentation-resources/Class-Diagram.png?raw=true "Title")

### Layers and Tiers diagram
We used the Bootstrap library, jQuery and JavaScript for the Presentation layer
On the  Business and Persistence Layer Java Spring Boot is used and on the DB tier runs a Mysql database

![Layers and Tiers](documentation-resources/Layers-and-Tiers.png?raw=true "Title")

## Mockup
### Mockup draft
To visualize the ideas and views, we want to generate, we created following mock-up:
![Class Diagram](documentation-resources/Mockup-draft.png?raw=true "Title")
The inital log-in page would be the same regardless the role of the user. However, the upcoming screens would differentiate based on Admin user (Librarian) or Normal User (Student). Generally, a corporate design with a collapsable sidebar should be always visible. <br />
The Librarian should have the main views of his/her profile, being able to look at book information, the "Borrow a book" page with the additional "Add book" button and see an overview of all borrowed books. <br />
The Student must get a overview of all books, where he/she can also search for a specific one; look at book information of books and get an overview of the profile. As they are not administrating, some buttons/functionalities do not have to be applicable. 

### Actual website
Looking at the mockup, the final result has been realized as intended with a corporate design and the possibility to hide the sidebar:
![Class Diagram](documentation-resources/Actual-Website.png?raw=true "Title")

## DB structure
[Edit DB diagram on lucid ](https://lucid.app/lucidchart/7dc561be-ccba-475d-8fea-9955b0b630bb/edit?viewport_loc=-168%2C-223%2C2130%2C1129%2C0_0&invitationId=inv_95025269-9fc5-4c7f-b6da-3e6556c2a036)
![DB Structure](documentation-resources/DB-Diagram.png?raw=true "Title")

## API request documentation
| Use Case | Request type | Request      | Content |
|----------|--------------|--------------|---------|
| UC3      | POST         | /myNewUser     | firstname, lastname, email, password |
| UC3      | GET         | /userRole     | role |
| UC4      | POST         | /login       | email, password |
| UC4      | GET         | /myUserProfile       | firstname, lastname, email |
| UC4      | POST         | /updateMyUserProfile       | firstname, lastname, email |
| UC5      | GET          | /allCategories| categories |
| UC5      | GET          | /allAuthors  | authorFirstname, authorLastname |
| UC5/6    | GET          | /allBooks    | title, description, year, currentlyBorrowed, category(ies), author(s), isbn |
| UC5      | GET          | /categoryBooks/<category_id>|  title, description, year, currentlyBorrowed, category(ies), author(s), isbn |
| UC5      | GET          | /categoryBooks/name/<category_id>|  categoryName |
| UC5      | GET          | /authorBooks/<author_id>|  title, description, year, currentlyBorrowed, category(ies), author(s), isbn |
| UC6      | GET          | /bookInfo/<book_id>  | title, description, year, currentlyBorrowed, category(ies), author(s), isbn|
| UC7      | POST         | /reserveBook/<book_id> | book_id, currentlyBorrowed |
| UC8      | GET          | /myBorrows   | title, description, year, currentlyBorrowed, category(ies), author(s), isbn , startDate, initEndDate, bookComment |
| UC8      | GET          | /bookedByMe/{bookId}   | currentlyBorrowed |
| UC10     | POST         | /newBook     | isbn, title, description, year, category, author |
| UC11/12     | GET          | /allBorrowed | title, description, year, currentlyBorrowed, category(ies), author(s), isbn , startDate, initEndDate, bookComment |
| UC18     | POST         | /addComment/<book_id> | comment|
| UC18     | GET         | /allComments/{bookId} | comment|
| UC20     | POST         | /returnBook/<book_id> | isbn, title, description, year, category(ies), author(s), currentlyBorrowed, startDate, initEndDate |


# Deployment to Heroku

[![Deploy to Heroku](https://img.shields.io/badge/deploy%20to-Heroku-6762a6.svg?longCache=true)](https://heroku.com/deploy)
This web application was deployed to Heroku. 
In order to make this work two main things needed to be done:
### Transfer of the code from GitLab to GitHub
Heroku supports the automatic deployment of GitHub repositories. However, as we started our work in GitLab we needed a work-around.
For this reason we firstly created a remote linking to a GitHub project and then we pushed the code to that repository - basically mirroring it.
```powershell 
git remote add github https://github.com/SalvoTr/ExplosivePomegranateDeployment.git
git push --mirror github
```
In case the push didn't work (403 error) this would be the solution:
```powershell 
git remote set-url origin https://SalvoTr@github.com/SalvoTr/ExplosivePomegranateDeployment.git 
```

Once done, it as simple as clicking a button on the Heroku web-site to connect GitHub with the Heroku project.

### Using ClearDB for MySQL
The next obstacle arose due to the fact that we opted for MySQL instead of an in-memory PostgreSQL solution.

A special Add-on needed to be installed in the Heroku project. in this case we chose **ClearDB**, one of multiple possible solutions.
Once installed, we were able to get the Confic Vars for the database from the Heroku settings tab.
![ConfigVars-ClearDB](documentation-resources/ConfigVars-ClearDB.png?raw=true "Title")

From that string starting with "mysql://" one can extract username, password, and hostname in order to create a new connection within the MySQL Workbench
![NewMySQLconnection](documentation-resources/MySQLconnection.png?raw=true "Title")

### Limitations of ClearDB
As we are using the free version of ClearDB we are limited to 3,600 queries per hour.
In a real scenario we would evidently opt out for a paid version as this limited amount of queries is quickly reached - especially considering how often all books from the library are fetched. Please bear this in mind when extensively testing the [deployed webpage](https://library-fhnw.herokuapp.com/) 


### Additional files
Finally, two more files were added in order to make the deployment together with the DB work 
- [Procfile](Procfile)
- [app.json](app.json)
 
