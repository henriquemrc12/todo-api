package com.example.todo.service

import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import com.example.todo.repository.TaskRepository
import com.example.todo.service.impl.TaskServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class TaskServiceTests {
    private lateinit var taskService: TaskService
    private lateinit var repository: TaskRepository

    @BeforeEach
    fun setUp() {
        repository = mock(TaskRepository::class.java)
        taskService = TaskServiceImpl(repository)
    }

    @Test
    fun testCreateTask() {
        val task = Task(UUID.randomUUID(), "Test Task", "Description", StatusTask.DOING, LocalDate.now())
        `when`(repository.save(task)).thenReturn(task)

        val createdTask = taskService.create(task)
        assertEquals(task, createdTask)
    }

    @Test
    fun testCreateTaskWithException() {
        val task = Task(UUID.randomUUID(), "Test Task", "Description", StatusTask.DOING, LocalDate.now())
        `when`(repository.save(task)).thenThrow(RuntimeException("Save failed"))

        assertThrows<RuntimeException> {
            taskService.create(task)
        }
    }

    @Test
    fun testUpdateTask() {
        val taskId = UUID.randomUUID()
        val existingTask = Task(taskId, "Existing Task", "Description", StatusTask.DOING, LocalDate.now())
        val updatedTask = Task(taskId, "Updated Task", "Updated Description", StatusTask.DONE, LocalDate.now())

        `when`(repository.findById(taskId)).thenReturn(Optional.of(existingTask))
        `when`(repository.save(existingTask)).thenReturn(updatedTask)

        val result = taskService.update(updatedTask)
        assertEquals(updatedTask, result)
    }

    @Test
    fun testUpdateTaskNotFound() {
        val taskId = UUID.randomUUID()
        val updatedTask = Task(taskId, "Updated Task", "Updated Description", StatusTask.DONE, LocalDate.now())

        `when`(repository.findById(taskId)).thenReturn(Optional.empty())

        assertThrows<Exception> {
            taskService.update(updatedTask)
        }
    }

}
