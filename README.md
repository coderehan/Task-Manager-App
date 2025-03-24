Task Manager App (Razorpay Android Assignment)

Problem Statement
The objective of this assignment is to develop an Android app for managing tasks while integrating with a third-party SaaS library and an external REST API (or a local JSON response).

Features:
Fetch tasks from a local JSON response (assets folder).

Display tasks using Jetpack Compose (LazyColumn).

CRUD operations: Add, Edit, Delete, and Mark tasks as completed.

Persist tasks using Room Database.

Firebase Analytics to track key events (Task Added, Task Edited, Task Completed).

Firebase Performance Monitoring for tracking app performance.

Firebase Crashlytics for crash reporting, including a simulated database error crash.

UI follows Material Design 3 principles.

 Tech Stack & Tools Used

Category	Technology
Language	Kotlin
UI Framework	Jetpack Compose
Architecture	MVVM (Model-View-ViewModel)
Local Database	Room Database
Networking	Local JSON Response (assets folder)
Dependency Injection	Hilt (if applicable)
Analytics & Crash Logs	Firebase Analytics, Crashlytics, Performance Monitoring
Version Control	Git & GitHub

Features Breakdown
✅ 1. API Integration (Local JSON Response)
Used a JSON file inside assets/ folder to load initial task data.

Parsed JSON using Gson and inserted data into Room Database.

✅ 2. Task Management (CRUD Operations)
Display Tasks → Used Jetpack Compose's LazyColumn to show tasks.

Add Task → Floating Action Button (FAB) opens Add Task screen.

Edit Task → Clicking a task opens an edit screen.

Delete Task → Swipe to delete or delete button implemented.

Mark as Completed → Checkbox to toggle completion status.

✅ 3. Firebase Integration
Firebase Analytics logs Task Added, Task Edited, Task Completed.

Firebase Performance Monitoring tracks API call/network response.

Firebase Crashlytics:

Manual Crash: Simulated crash for testing.

Database Error Crash: Simulated Room DB failure for tracking.

✅ 4. UI/UX Enhancements
Material Design 3 components for a modern look.

Snackbar messages for error/success feedback.

