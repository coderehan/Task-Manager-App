package com.rehan.taskmanagerapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.taskmanagerapp.data.model.Task
import com.rehan.taskmanagerapp.data.repository.TaskRepository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject
import kotlin.apply

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val firebaseAnalytics: FirebaseAnalytics
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    val tasks: StateFlow<List<Task>> = _tasks

    init {
        viewModelScope.launch {
            repository.loadTasksFromJson() // Load initial data from JSON if DB is empty
            repository.getAllTasks().collect { _tasks.value = it }
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            val newTask = Task(title = title, isCompleted = false)
            repository.addTask(newTask)

            // Log event to Firebase Analytics
            val bundle = Bundle().apply {
                putString("task_title", title)
            }
            firebaseAnalytics.logEvent("Task_Added", bundle)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)

            // Log event to Firebase Analytics
            val bundle = Bundle().apply {
                putString("task_title", task.title)
                putBoolean("task_completed", task.isCompleted)
            }
            firebaseAnalytics.logEvent("Task_Edited", bundle)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)

            // Log event to Firebase Analytics
            val bundle = Bundle().apply {
                putString("task_title", task.title)
            }
            firebaseAnalytics.logEvent("Task_Deleted", bundle)
        }
    }

    fun markTaskAsCompleted(taskId: Int, taskTitle: String) {
        viewModelScope.launch {
            repository.markTaskAsCompleted(taskId) // Pass only the taskId

            // Log task completion event to Firebase Analytics
            val bundle = Bundle().apply {
                putString("task_title", taskTitle)
                putBoolean("task_completed", true)
            }
            firebaseAnalytics.logEvent("Task_Completed", bundle)
        }
    }

    fun simulateAppCrash() {
        throw RuntimeException("Test Crash - App Crashed!")
    }

    fun simulateDatabaseCrash() {
        viewModelScope.launch {
            try {
                repository.addTask(Task(id = -1, title = "Invalid Task", isCompleted = false))
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e) // Log crash to Crashlytics
            }
        }
    }
}
