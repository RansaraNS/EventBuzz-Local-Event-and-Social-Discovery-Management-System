# 🎉 EventBuzz - Local Event & Social Discovery Management System

![Project Status](https://img.shields.io/badge/status-in--development-yellow)

> ⚠️ This project is currently under active development. Expect rapid changes, new features, and frequent commits.


**EventBuzz** is a modern, location-based social discovery platform that connects people through real-world events. Designed for both attendees and organizers, it blends social networking, event management, and discovery into one seamless experience.

---

## 🌟 App Concept Deep Dive

### 🎯 Core Vision

EventBuzz is a location-based social discovery platform that connects people through local events, fostering community engagement and helping users discover experiences tailored to their interests.

### 👥 Target Audience

- **Primary:** Young professionals (22–35) seeking social connections  
- **Secondary:** Event organizers, local businesses, community groups  
- **Tertiary:** Tourists and visitors exploring new cities  

---

## 🧩 Core Features Breakdown

### 1. 👤 User Management System

- **User Registration/Login**
  - Email/Phone verification
  - Social login (Google, Facebook)
  - Onboarding with interest and location preferences

- **Profile Management**
  - Bio, photos, interest tags
  - Privacy controls (event visibility)
  - Verified organizer badges

---

### 2. 🔍 Event Discovery Engine

- **Location-Based Search**
  - GPS integration for nearby events
  - Radius filtering (1km, 5km, 10km, city-wide)
  - Interactive Map View

- **Smart Filtering System**
  - Filter by date/time (today, this weekend, next week)
  - Filter by category (music, food, sports, networking, art, tech)
  - Filter by price (free, under $20, premium)
  - Filter by group size (intimate <20, medium 20–100, large 100+)

---

### 3. 🛠️ Event Creation & Management

- **Event Creation Wizard**
  - Title, description, date/time
  - Location picker with map integration
  - Category and tag selection
  - Pricing/ticketing support
  - Media uploads (photo/video)

- **Event Management Dashboard**
  - Attendee tracking
  - Real-time event analytics
  - Messaging tools for attendees
  - Check-in system with QR code support

---

### 4. 🤝 Social Features

- **RSVP System**
  - Going / Interested / Not Going statuses
  - Plus-one invitations
  - Waitlist system for full events

- **Social Interactions**
  - Follow users & organizers
  - Event reviews & star ratings
  - Photo sharing from events
  - Comment threads for discussions

- **Friend System**
  - Connect with other attendees
  - See friends’ event activity
  - Group event planning

---

### 5. 🎯 Recommendation Engine

- **Personalized Suggestions**
  - Based on previous attendance
  - Matches with user interests
  - Friend activity and behavior
  - Popularity and local trends

- **Trending Events**
  - Most attended this week
  - Emerging/rising events
  - Last-minute discovery options

---

## 🛠️ Technology Stack Overview

### Frontend: **React Native**

- Cross-platform: iOS & Android from a single codebase  
- Near-native performance  
- Vibrant ecosystem with reusable components  
- Cost-effective development

### Backend: **Spring Boot**

- Robust, production-ready framework  
- RESTful API support  
- Built-in security & JWT authentication  
- Modular & scalable for microservices

### Database: **MySQL**

- Stable and reliable relational DBMS  
- Well-suited for structured, relational data  
- Scalable with replication options  
- Efficient read-heavy performance

---

## 🏗️ Architecture Overview
[ React Native App ]
↕
[ REST API - Spring Boot ]
↕
[ MySQL Database ]
↕
[ External APIs (Map, Payments, Notifications) ]


---

## ⚙️ Liquibase Setup Guide

To enable database version control using **Liquibase**, follow these steps to install and configure the CLI globally on your system:

### ✅ Install Liquibase CLI Globally

1. **Download Liquibase CLI (ZIP)**  
   👉 https://www.liquibase.org/download

2. **Extract the ZIP**  
   Extract to a desired location, for example:  
   `C:\liquibase`

3. **Add Liquibase to System PATH**  
   - Open: `System Properties > Environment Variables`
   - Under **System Variables**, find `Path` → Click **Edit**
   - Add new entry: `C:\liquibase`

4. **Verify Installation**  
   Open your terminal (CMD or PowerShell) and run:

   ```bash
   liquibase --version

