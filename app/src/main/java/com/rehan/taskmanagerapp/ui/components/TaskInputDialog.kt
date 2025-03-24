package com.rehan.taskmanagerapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.rehan.taskmanagerapp.data.model.Task

@Composable
fun TaskInputDialog(
    showDialog: Boolean,
    initialTask: Task? = null,
    onDismiss: () -> Unit,
    onTaskSaved: (Task) -> Unit
) {
    if (showDialog) {
        var inputText by remember { mutableStateOf(initialTask?.title ?: "") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(if (initialTask == null) "Add Task" else "Edit Task") },
            text = {
                Column {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("Task Name") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (inputText.isNotBlank()) {
                        val newTask = initialTask?.copy(title = inputText)
                            ?: Task(title = inputText, isCompleted = false)
                        onTaskSaved(newTask)
                        onDismiss()
                    }
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) { Text("Cancel") }
            }
        )
    }
}

@Preview
@Composable
fun TaskInputDialogPreview() {
    TaskInputDialog(showDialog = true, onDismiss = {}, onTaskSaved = {})
}
