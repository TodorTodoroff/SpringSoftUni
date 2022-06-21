# Spring Fundamentals Exam

# Battle Ships Application

Exam for the [&quot;Spring Fundamentals&quot; course @ SoftUni.](https://judge.softuni.org/Contests/3234/Spring-Fundamentals-Exam-24-Oct-2021)

The video game market is quite oversaturated but there is always room for another clone of one of the most famous movie series. A group of friends decided to try to develop this game, but they need your help to implement it. The idea is clear but due to the lack of technical knowledge from your colleagues you will have to create the business logic for this project.


## Database Requirements

The **Database** of the **Battle Ships** application needs to support **3 entities** :

### User

- **Id – Accepts UUID-String or Long values**
- **Username**
  - The **length** of the **values** should be **between 3** and **10** characters long (both numbers are **INCLUSIVE** )
  - The values should be **unique** in the database
- **Full Name**
  - The **length** of the **values** should be **between 5** and **20** characters long (both numbers are **INCLUSIVE** )
- **Password**
  - The **length** of the **values** should be more than 3 characters long ( **INCLUSIVE** )
- **Email **
  - The values should contain a **&#39;@&#39;** symbol
  - The values should be **unique** in the database

### Ship

- **Id**  **– Accepts UUID-String or Long values**
- **Name**

  - The **length** of the **values** must be **between 2** and **10** characters (both numbers are **INCLUSIVE** )
  - The values should be **unique** in the database

- **Health**
  - The **values** should be **positive numbers**
- **Power**
  - The **values** should be **positive numbers**
- **Created **
  - The **values** should not be **future dates**
- **Category**
  - A **relationship** with **the Categories Entity**
- **User**
  - A user that **owns the ship**

### Category

- **Id**  **– Accepts UUID-String or Long values**
- **Name**

  - An option between **BATTLE, CARGO** , **PATROL**
  - The values should be **unique** in the database
- **Description **
  - A very long and detailed description of the category
  - Can accept **null values**

    -

**Nullable/Empty values are not allowed unless explicitly mentioned.**

Implement the entities with the **correct data types** and implement the **repositories** for them.

Here is the ER Diagram:

![image](https://user-images.githubusercontent.com/78209454/174778743-5c6f34f2-a39c-4bf7-88d6-9f38f22b096d.png)![image](https://user-images.githubusercontent.com/78209454/174778773-9b5f6af6-f440-43b0-9ead-888bddbf1e12.png)



## Initialize categories

- Implement a method that checks (when app started) if the database does not have any category and
initialize them
  - You are free to do this in some different ways.
  - You can skip the description if you want


## Page Requirements

### Index Page (logged out user)

![image](https://user-images.githubusercontent.com/78209454/174778801-12f412b6-e831-4f52-926b-39b40536bba7.png)

### Login Page (logged out user)

![image](https://user-images.githubusercontent.com/78209454/174778839-69497406-1506-4d09-a0ec-76c3fe90f1ab.png)

### Login Page validations

![image](https://user-images.githubusercontent.com/78209454/174778976-d936dc05-42ef-4ff2-9573-4b72f2f216b1.png)
![image](https://user-images.githubusercontent.com/78209454/174778988-688f9438-e31d-45d0-8f41-397ebb3ae986.png)

### Register Page (logged out user)


### Register Page validations

- Note: it is not necessary to show message for different passwords, just not save the user and redirect again to the register page.

![image](https://user-images.githubusercontent.com/78209454/174779139-fc53e31d-3977-411c-a2c5-2fc15e81d848.png)

### Navigation (Guest user)

- Note: can access only to **Index** , **Login** , **Register** pages.

![image](https://user-images.githubusercontent.com/78209454/174779173-7899eab7-05ec-4955-832c-84d5c0586c2a.png)

### Navigation (Registered user)

- Note: can access only to **Home** , **Add Ship** , **Logout**.
- 
![image](https://user-images.githubusercontent.com/78209454/174779193-9880abc9-81c3-43d6-9f9d-94e9c838f75a.png)

### Add Ship

![image](https://user-images.githubusercontent.com/78209454/174779217-5e794328-6d23-468d-b5a1-d3636bd26a06.png)

### Add Ship validation

![image](https://user-images.githubusercontent.com/78209454/174779237-ecd39f4f-6280-46b4-9b22-a57dedd8c85e.png)

### Home Page

![image](https://user-images.githubusercontent.com/78209454/174779262-b557268c-3f96-43a3-9740-bd079ba6640c.png)

**NOTE:** You must select **one** of the ships that are **owned** by the **current user**.

**NOTE: ** You must select  **one**  of the ships that are  **owned**  by  **other users**.

**NOTE:** In the last section you should list all the ships **ordered by** their status (name, health, power) in the following format:

**name** -- **health** -- **power**.

**NOTE:** When pressing the **fire button** , the **attacker**** hits **the** defender **and** reduces **his health by the value of the attacker&#39;s** power **.  If the defender&#39;s health is** less than **or** equal **to** 0 **,** remove** their ship from the database. After the attack, the application must redirect again to the home page.

The templates have been given to you in the application skeleton, so make sure you implement the pages correctly.

**NOTE** : The templates should look **EXACTLY** as shown above.

**NOTE** : The templates do **NOT**** require ****additional**** CSS **for you to write. Only the provided** bootstrap **and** CSS** are enough.


## Functional Requirements

The **Functionality Requirements** describe the functionality that the **application** must support.

The **application** should provide **Guest** (not logged in) users with the functionality to **login** , **register** and view the **Index** page.

The **application** should provide **Authenticated** (logged in) users with the functionality to **logout** , **add a ship** , view **home** page and ready to **fire** at other ships.

In the **BattleShips Application** , the navbar should redirect to the appropriate **URL depending** on if the user is logged in.

The **application** should provide the **functionality** for **adding ships** with **categories** and **users**. Also, the ships should **fire** at other ships and **remove** them from the database when their health is **lower**** than **or** equal** to 0.

The **Fire** button **creates** the **attack** to the defender and **redirects** to the home page.

The **application** should **store** its **data** into a **MySQL** database.


## Security Requirements

The **Security Requirements** are mainly access requirements. Configurations about which users can access specific functionalities and pages.

- **Guest** (not logged in) users can access **Index** page.
- **Guest** (not logged in) users can access **Login** page.
- **Guest** (not logged in) users can access **Register** page.
- **Users** (logged in) can access **Home** page.
- **Users** (logged in) can access **Add Ship** page.
- **Users** (logged in) can access **Logout** functionality.



![Shape1](RackMultipart20220621-1-fuguss_html_90c110eb660a4dd9.gif) ![Shape2](RackMultipart20220621-1-fuguss_html_5f0f2ddacbac70d2.gif) ![Shape3](RackMultipart20220621-1-fuguss_html_51bd00be29b85496.gif) ![Shape4](RackMultipart20220621-1-fuguss_html_f746d52952cd7e91.gif)[![](RackMultipart20220621-1-fuguss_html_3aa486326bfa75e9.png)](https://softuni.org/)

© SoftUni – [about.softuni.bg](https://about.softuni.bg/). Copyrighted document. Unauthorized copy, reproduction or use is not permitted.

