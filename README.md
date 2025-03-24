<h2>Task Manager App (Razorpay Android Assignment)</h2>

<h3>Problem Statement:</h3>
The objective of this assignment is to develop an Android app for managing tasks while integrating with a third-party SaaS library and an external REST API (or a local JSON response).

<h3>Features:</h3>
Fetch tasks from a local JSON response (assets folder). <br>
Display tasks using Jetpack Compose (LazyColumn). <br>
CRUD operations: Add, Edit, Delete, and Mark tasks as completed. <br>
Persist tasks using Room Database. <br>
Firebase Analytics to track key events (Task Added, Task Edited, Task Completed). <br>
Firebase Performance Monitoring for tracking app performance. <br>
Firebase Crashlytics for crash reporting, including a simulated database error crash. <br>
UI follows Material Design 3 principles. <br>

 <h3>Tech Stack & Tools Used:</h3>
Language: Kotlin <br>
UI Framework:	Jetpack Compose <br>
Architecture:	MVVM (Model-View-ViewModel) <br>
Local Database:	Room Database <br>
Networking:	Local JSON Response (assets folder) <br>
Dependency Injection:	Hilt <br>
Analytics & Crash Logs:	Firebase Analytics, Crashlytics, Performance Monitoring <br>
Version Control:	Git & GitHub <br>

<h3>Features Breakdown: </h3>
✅ 1. API Integration (Local JSON Response <br>
Used a JSON file inside assets/ folder to load initial task data. <br>
Parsed JSON using Gson and inserted data into Room Database. <br>
✅ 2. Task Management (CRUD Operations) <br>
Display Tasks → Used Jetpack Compose's LazyColumn to show tasks. <br>
Add Task → Floating Action Button (FAB) opens Add Task screen. <br>
Edit Task → Clicking a task opens an edit screen. <br>
Delete Task → Swipe to delete or delete button implemented. <br>
Mark as Completed → Checkbox to toggle completion status. <br>
✅ 3. Firebase Integration <br>
Firebase Analytics logs Task Added, Task Edited, Task Completed. <br>
Firebase Performance Monitoring tracks API call/network response. <br>
Firebase Crashlytics: <br>
Manual Crash: Simulated crash for testing. <br>
Database Error Crash: Simulated Room DB failure for tracking. <br>
✅ 4. UI/UX Enhancements <br>
Material Design 3 components for a modern look. <br>
Snackbar messages for error/success feedback. <br>

