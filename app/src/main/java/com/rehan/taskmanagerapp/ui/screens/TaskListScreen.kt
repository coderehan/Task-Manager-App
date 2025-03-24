package com.rehan.taskmanagerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rehan.taskmanagerapp.data.model.Task
import com.rehan.taskmanagerapp.ui.components.TaskInputDialog
import com.rehan.taskmanagerapp.ui.components.TaskItem
import com.rehan.taskmanagerapp.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                taskToEdit = null // Reset editing state
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onUpdate = {
                            taskToEdit = task
                            showDialog = true
                        },
                        onDelete = {
                            viewModel.deleteTask(task)
                        },
                        onComplete = {
                            viewModel.markTaskAsCompleted(task.id, task.title)
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        TaskInputDialog(
            showDialog = showDialog,
            initialTask = taskToEdit,
            onDismiss = { showDialog = false },
            onTaskSaved = { updatedTask ->
                if (taskToEdit == null) {
                    viewModel.addTask(updatedTask.title)
                } else {
                    viewModel.updateTask(updatedTask)
                }
                showDialog = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}
