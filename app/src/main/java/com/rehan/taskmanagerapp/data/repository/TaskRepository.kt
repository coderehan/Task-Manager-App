package com.rehan.taskmanagerapp.data.repository

import android.content.Context
import com.rehan.taskmanagerapp.data.local.TaskDao
import com.rehan.taskmanagerapp.data.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.Trace
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao, private val context: Context) {

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) {
        val trace: Trace = FirebasePerformance.getInstance().newTrace("add_task_trace")
        trace.start()
        taskDao.insertTask(task)
        trace.stop()
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun markTaskAsCompleted(taskId: Int) {
        taskDao.markTaskAsCompleted(taskId) // Directly updates `isCompleted`
    }

    suspend fun loadTasksFromJson() {
        if (taskDao.getTaskCount() == 0) {
            val jsonString = context.assets.open("task.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Task>>() {}.type
            val taskList: List<Task> = Gson().fromJson(jsonString, type)
            taskDao.insertAll(taskList)
        }
    }
}
