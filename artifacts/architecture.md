# Program Organization

## System Context Diagram

![System Context Diagram](/images/SystemContextDiagram.png) 
A simple System Context Diagram, showing the interactions of the Software System

## Container Diagram

![Container Diagram](/images/ContainerDiagram.png) 
A zoom into the Moment of Muscle system, showing how it will use a mobile app and how it will comunicate with the database

## Component Diagram

![Component Diagram](/images/ComponentDiagram.png) 
The structural blocks of the mobile app, showing the functions the app will have and their link to the database

## User stories - Blocks relationship

![User stories - Blocks relationship](/images/User-Block-Relation.png) 
A table with lines connecting which block solves each user stories.

# Code Design

## Class Diagram

![Class Diagram](/images/UMLClassDiagram.jpg)

Class Diagram that includes the required objects for a basic login system, job posting and accepting system. The User object includes the name, bio and rating of the client and calls the methods isUserSignedIn, createNewUsers and resetPassword as needed on the login screen. A Worker and Hiring Manager object inherit the User object but also include the field currentJobs/currentWorkers and the methods searchForWork/postNewJob respectively. 

## UML Sequence Diagram
![UML Sequence Diagram](/images/UMLSequenceDiagram.jpg)

The UML Sequence Diagram provides a visual presentation of the interactions that are taking place within our system. The diagram includes three objects included in the diagram (client, login UI, SQL database) and displays the process of search for preexisting jobs and accepting. The diagram also details what happens if specific conditions aren’t met. All three of these objects are included in the diagram because they are all needed to complete a job request transaction. 

## Activity Diagram #1 (isUserSignedIn)
![Activity Diagram #1 (isUserSignedIn)](/images/isUserSignedIn.jpg)
This activity diagram details the pipeline for handling the user login process. This class includes calls to subclasses createNewUsers and resetPassword under specific conditions. At the end of this class, a user should be able to successfully login and prepare to take job offerings. 

## Activity Diagram #2 (createNewUser)
![Activity Diagram #2 (createNewUser)](/images/createNewUser.jpg)
This activity diagram includes a subclass of isUserSignedIn and requests sufficient information from the user to create a new account. This class also handles the case where a user tries to create a new account with credentials that belong to another user.

## Activity Diagram #3 (resetPassword)
![Activity Diagram #3 (resetPassword)](/images/resetPassword.jpg)
This activity diagram includes a subclass of createNewUser and isUserSignedIn and handles the resetting of a users password. 

## Relating to User Stories
![Relating to User Stories](/images/classuserstory.jpg)

# Data Design

[Data Activity Diagram](https://cdn.discordapp.com/attachments/804845091570319380/810274680267210822/unknown.png)
The following entities within this diagram are the ‘user’, job, ‘location’, and ‘account’. The moment of muscle app is a community-base app, as it allows others to come help people who are in need of assistance with jobs that they are unable to do. The ‘account’ entity has the attributes of the point system, and a job list. This one entity has a relationship with three separate entities, with location being important as users will have to know where they are. The Job entity is also important as jobs are the main feature of the app, with it’s traits being the required experience for the job and the name of it. The final entity is the User entity with the simple traits of a username, id-code and the password. 

See Code Complete, Chapter 3

# Business Rules

Business rules and decisions will be decided by the 5 developers working on Moment of Muscle (MOM). We will adhere to Dr. Hollanders software development guidelines.

# User Interface Design
[Here is a link to some user interface mockups](https://cdn.discordapp.com/attachments/804845091570319380/810342429139402752/Diagram.jpg)

For the screen on the left, the general purpose of this is to provide an entry gate for users to log into the app and thus be identified under a general profile containing their information as a user. Users will type an appropriate username and its accompanying password in order to log in, or click either the reset password button if they have forgot their information, or the “create account” app if they have never logged in with the application before. This relates to the user story “As a user, I want to log in, so that I am able to use the app”, as this screen directly allows them to complete this task.

For the screen in the middle, the general purpose of this is to allow users to select what primary function of the app they would like to engage with. Whether they want to hire someone for work, be hired for work, or edit their personal profile, every extremely important option is available on this screen. This relates to three different user stories, those being “As a user looking for help, I want to hire people for small jobs”, “As a user looking for work, I want to be able to apply for jobs”, and “As a user, I want to create my profile page to hire or work”. This screen links to pages that relate to all three of these functionalities, and thus is an extremely important junction for making the app function as a whole.

Finally, for the screen on the right, the general purpose of this is to allow users to rate the general performance of others once a job is completed. As a worker, you are rating the general performance of the one who hired you in terms of cooperation and helpful feedback, and as someone who hired a worker, you are rating them on task completion and general performance in completing your desired goal. This relates to the user story “As a user, I want to rate other users so that good users are better seen by other users”, as these ratings will be added to the respective person’s profile pages, so that they can be reviewed by others using the application.

Outside of these three specific pages though, our user interface diagram provides additional details as to how every page of the application is related, and what buttons and functions link between certain pages. This diagram can be seen [here](https://cdn.discordapp.com/attachments/804845091570319380/810343103318720522/UI_Diagram.png), and more concisely describes the relationships between all of our individual pages. The above three screens were arguably the most important ones of the application though, which is why they were chosen to be featured.

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

