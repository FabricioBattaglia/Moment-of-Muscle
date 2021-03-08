# Program Organization

## System Context Diagram

![System Context Diagram](/images/SystemContextDiagram.png) 

A simple System Context Diagram, showing the interactions of the Software System

## Container Diagram

![Container Diagram](/images/UpdatedContainerDiagram.png) 

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

## Data Design

![Data Activity Diagram](/images/MomentofMuscleDatabase.JPG)
The following entities within this diagram are the ‘user’, job, ‘location’, and ‘account’. The moment of muscle app is a community-base app, as it allows others to come help people who are in need of assistance with jobs that they are unable to do. The ‘account’ entity has the attributes of the point system, and a job list. This one entity has a relationship with three separate entities, with location being important as users will have to know where they are. The Job entity is also important as jobs are the main feature of the app, with it’s traits being the required experience for the job and the name of it. The final entity is the User entity with the simple traits of a username, id-code and the password. 


# Business Rules

Business rules and decisions will be decided by the 5 developers working on Moment of Muscle (MOM). We will adhere to Dr. Hollanders software development guidelines.

# User Interface Design

## Account Creation Screen 

![here](/images/register.JPG)

## Login Screen Design 

![here](/images/login.JPG)

## Home Screen Design 

![here](/images/home.JPG)

## Profile Screen Design 

![here](/images/profile.JPG)

## Job Board Screen
![here](/images/job_board.JPG)

For the screen on the top left, the general purpose of this is to provide an entry gate for users to log into the app and thus be identified under a general profile containing their information as a user. Users will type an appropriate email and its accompanying password in order to log in, or click either the “Forgot password” button if they have forgotten their information, or the “New here? Create account” app if they have never logged in with the application before. This relates to the user story “As a user, I want to log in, so that I am able to use the app”, as this screen directly allows them to complete this task.

For the screen on the top right, the general purpose of this is to allow users to rate the general performance of others once a job is completed. As a worker, you are rating the general performance of the one who hired you in terms of cooperation and helpful feedback, and as someone who hired a worker, you are rating them on task completion and general performance in completing your desired goal. This relates to the user story “As a user, I want to rate other users so that good users are better seen by other users”, as these ratings will be added to the respective person’s profile pages, so that they can be reviewed by others using the application. When our app is complete, a person’s profile information will be displayed on this screen as well, but we are still working on finalizing said profiles.

For the screen on the top right, the general purpose of this is to allow users to input the title of a job for the sake of other users finding said job to work. As we develop, we may add more details to this screen such as adding details to the job viewable upon clicking its name, and a pop-up of the profile of whoever sent out the job. For now though, we have focused on getting the base framework down, so this is what we have currently. This screen relates to the user story “As a user looking for help, I want to hire people for small jobs”, as this is the actual screen where you design a job for other users to find and get hired for.

For the screen on the bottom left, the general purpose of this is to allow users to select what primary function of the app they would like to engage with. Whether they want to hire someone for work, be hired for work, or edit their personal profile, every extremely important option will be available on this screen. We are still working on developing screens that every button can link to (which is why the button linking to the previously described screen is not there yet), but on completion this will be present and fully realized. This relates to three different user stories, those being “As a user looking for help, I want to hire people for small jobs”, “As a user looking for work, I want to be able to apply for jobs”, and “As a user, I want to create my profile page to hire or work”. This screen links to pages that relate to all three of these functionalities, and thus is an extremely important junction for making the app function as a whole.

Finally, for the screen in the bottom middle, the functionality is near identical to the first listed screen, except that in addition to logging the user in, it also creates their account to make future logins a simplified process, now only requiring the original first screen. Users will type their full name, email, password, and phone number for their account in the signified boxes - the email and password will be used for future logins on the first screen, and the email, name, and phone number will be added to the person’s profile page, as a basis for contact. If they clicked the button to reach this screen by mistake and already have an account, users can click the “Already registered? Login here” text to go back to the first login screen. This screen is tied to the user stories “As a user, I want to log in, so that I am able to use the app”, since creating an account will let a user log in, and “As a user, I want to create my profile page to hire or work so that other users can learn a bit about me before a job.”, since creating an account will give you access to edit your own personal profile page.


# Resource Management 
The Moment of Muscle application manages their own resources at the moment and is being developed on Android Studio.

# Security 
Currently, security is not a concern as it is outside of our scope. However, we will be storing data in a MySQL database. We will consider standard data encryption practices when dealing with accounts/user information. Users will be able to recover their username/password if lost/stolen.

# Performance 
Performance is not a concern as it is outside of our scope. Currently, we are trying to create a basic functional Android application and performance is not a focus point at the moment as the developers are the only users. Additionally, we are only using test data and thus the need to optimize performance is unnecessary. 

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
Our database will use MySQL and will interact with the System software using the JTDS Library. Additionally, we are utilizing the Firebase framework to handle user accounts on the application. The development environment for Moment of Muscle (MOM) will be done on Android Studio, which is a free open source application.

# Reuse
We will test all pre existing software, test cases, data formats and other materials before releasing anything. Making sure first and foremost that the reused software aligns to our architecture goals.

# Change Strategy
Because building a software product is a learning process for both the programmers and the users, the product is likely to change throughout its development. Results from testing phases will also contribute to how we change the direction and scope of the application in the coming weeks.

