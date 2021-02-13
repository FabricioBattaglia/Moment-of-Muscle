Populate each section with information as it applies to your project. If a section does not apply, explain why. Include diagrams (or links to diagrams) in each section, as appropriate.  For example, sketches of the user interfaces along with an explanation of how the interface components will work; ERD diagrams of the database; rough class diagrams; context diagrams showing the system boundary; etc. Do _not_ link to your diagrams, embed them directly in this document by uploading the images to your GitHub and linking to them. Do _not_ leave any section blank.

# Program Organization

[System Context Diagram](https://cdn.discordapp.com/attachments/776175060896841752/809574937463226368/SystemContextDiagram.png)
A simple System Context Diagram, showing the interactions of the Software System

[Container Diagram](https://cdn.discordapp.com/attachments/804845091570319380/810244649082945566/ContainerDiagram.png)
A zoom into the Moment of Muscle system, showing how it will use a mobile app and how it will comunicate with the database

[Component Diagram](https://cdn.discordapp.com/attachments/776175060896841752/809574930277728326/ComponentDiagram.png)
The structural blocks of the mobile app, showing the functions the app will have and their link to the database

[User stories - Blocks relationship](https://cdn.discordapp.com/attachments/804845091570319380/810260575963316224/User-Block-Relation.png)
A table with lines connecting which block solves each user stories.

# Code Design

[Class Diagram](https://cdn.discordapp.com/attachments/804845091570319380/810267117931987025/UMLClassDiagram.jpg)
:Class Diagram that includes the required objects for a basic login system, job posting and accepting system. The User object includes the name, bio and rating of the client and calls the methods isUserSignedIn, createNewUsers and resetPassword as needed on the login screen. A Worker and Hiring Manager object inherit the User object but also include the field currentJobs/currentWorkers and the methods searchForWork/postNewJob respectively. 

[UML Sequence Diagram](https://cdn.discordapp.com/attachments/804845091570319380/810220594817662976/UMLSequenceDiagram.jpg)
:The UML Sequence Diagram provides a visual presentation of the interactions that are taking place within our system. The diagram includes three objects included in the diagram (client, login UI, SQL database) and displays the process of search for preexisting jobs and accepting. The diagram also details what happens if specific conditions aren’t met. All three of these objects are included in the diagram because they are all needed to complete a job request transaction. 

[Activity Diagram #1 (isUserSignedIn)](https://cdn.discordapp.com/attachments/804845091570319380/810220615897710602/isUserSignedIn.jpg)
:This activity diagram details the pipeline for handling the user login process. This class includes calls to subclasses createNewUsers and resetPassword under specific conditions. At the end of this class, a user should be able to successfully login and prepare to take job offerings. 

[Activity Diagram #2 (createNewUser)](https://cdn.discordapp.com/attachments/804845091570319380/810220632527863879/createNewUser.jpg)
:This activity diagram includes a subclass of isUserSignedIn and requests sufficient information from the user to create a new account. This class also handles the case where a user tries to create a new account with credentials that belong to another user.

[Activity Diagram #3 (resetPassword)](https://cdn.discordapp.com/attachments/804845091570319380/810220646901743676/resetPassword.jpg)
:This activity diagram includes a subclass of createNewUser and isUserSignedIn and handles the resetting of a users password. 

[Relating to User Stories](https://cdn.discordapp.com/attachments/804845091570319380/810275104147505182/classuserstory.jpg)

# Data Design

If you are using a database, you should have a basic Entity Relationship Diagram (ERD) in this section. This diagram should describe the tables in your database and their relationship to one another (especially primary/foreign keys), including the columns within each table. 

See Code Complete, Chapter 3

# Business Rules

You should list the assumptions, rules, and guidelines from external sources that are impacting your program design. 

See Code Complete, Chapter 3

# User Interface Design

You should have one or more user interface screens in this section. Each screen should be accompanied by an explaination of the screens purpose and how the user will interact with it. You should relate each screen to one another as the user transitions through the states of your application. You should also have a table that relates each window or component to the support using stories. 

See Code Complete, Chapter 3

# Resource Management 
The Moment of Muscle application manages their own resources at the moment and is being developed on Android Studio.

# Security 
We will be storing data in a MySQL database. We will use standard data encryption practices when dealing with accounts/user information. Users will be able to recover their username/password if lost/stolen.

# Performance 
Our performance goals are for the application to be lightweight and have short and direct features, which are meaningful and easy for users to use. This will save a lot on the overhead of the project and result in a quick application.

# Scalability 
We plan to make Moment of Muscle available on the Android app store for users to download. We have a clear and defined plan for our architecture to meet future demand. When hitting a certain number of users we plan to get more servers and continually test our application to make sure that our database records are fast/accurate. Moment of Muscle is positioning itself to be able to accept large quantities of users. Our 5 developers are the only ones with access to Moment of Muscle at the moment, so scaling is not currently an issue in our development phase. 

# Interoperability
We are going to use JTDS.jar library for connecting with Database our Android Application to MS SQL Database. 
Internationalization/Localization
Since the developers are also the users at this point, there is no reason to display error/help/warning boxes or messages.

# Input/Output 
The following 3 tables below will interact together to be able to create an Account, set up a profile and post/accept jobs. Each account will also have a profile. After having an account setup you will be able to post or accept jobs.
To set up an account you will input your username and password and email. After your account creation is finished, you will be prompted to create a profile. All the fields in the Profile are listed below. After that the user will have the ability to accept or post jobs.
 
Account
-UserID_PK
-Username
-Password
-Email

Profile
-UserID_FK
-FirstName
-LastName
-City
-State
-Zip
-Address
-Categories
-Bio
-Rating

Jobs
-UserID_FK
-Category
-TimeStamp
-Worker

# Error Processing 
Error processing will be done when the user enters a false username or password, or attempts to create an account that already has that username existing in the Moment of Muscle database. At that point text will prompt the user directions.
• The system will back up and try again when it detects a fault. If the first answer is wrong, it would back up to a point at which it knew everything was all right and continue from there.
• The system will have auxiliary code to use if it detects a fault in the primary code.

# Architectural Feasibility 
We do not have any concerns with Moment of Muscles architectural feasibility. At the moment we are just pulling from a MySQL database and are testing the application with a low amount of data to make sure everything is set up for scale.

# Overengineering
Moment of Muscle is being designed keeping in mind KISS (Keep it simple, stupid), doing only the necessary code for the fundamental parts of the code.

# Build-vs-Buy Decisions 
Our database will use MySQL and will interact with the System software using the JTDS Library. The development environment for Moment of Muscle (MOM) will be done on Android Studio, which is a free open source application.

# Reuse
We will test all pre existing software, test cases, data formats and other materials before releasing anything. Making sure first and foremost that the reused software aligns to our architecture goals.

# Change Strategy
Because building a software product is a learning process for both the programmers and the users, the product is likely to change throughout its development. 

