Database Queries for BANK MANAGEMENT SYSTEM Project

This document contains all SQL commands required to set up the database for the Bank Management System project in MySQL.


---

🏦 1. Create Database
```
create database bankmanagementsystem;
```

---

📂 2. Select the Database
```
use bankmanagementsystem;
```

---

📝 3. Create signup Table (User Basic Details)
```
create table signup(
    formno varchar(20),
    name varchar(20),
    father_name varchar(20),
    dob varchar(20),
    gender varchar(20),
    email varchar(30),
    marital_status varchar(20),
    address varchar(40),
    city varchar(25),
    pincode varchar(20),
    state varchar(25)
);
```

---

📝 4. Create signuptwo Table (Additional User Details)
```
create table signuptwo(
    formno varchar(20),
    religion varchar(20),
    category varchar(20),
    income varchar(20),
    education varchar(20),
    occupation varchar(20),
    pan varchar(20),
    aadhar varchar(20),
    seniorcitizen varchar(20),
    existingaccount varchar(20)
);
```

---

📝 5. Create signupthree Table (Account Information)
```
create table signupthree(
    formno varchar(20),
    accountType varchar(40),
    cardnumber varchar(25),
    pin varchar(10),
    facility varchar(100)
);
```

---

🔐 6. Create login Table (Authentication Details)
```
create table login(
    formno varchar(20),
    cardnumber varchar(25),
    pin varchar(10)
);
```

---

💸 7. Create bank Table (Transaction Details)
```
create table bank(
    pin varchar(10),
    date varchar(50),
    type varchar(20),
    amount varchar(20)
);
```
