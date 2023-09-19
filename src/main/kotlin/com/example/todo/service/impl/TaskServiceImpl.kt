package com.example.todo.service.impl

import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import com.example.todo.repository.TaskRepository
import com.example.todo.service.TaskService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class TaskServiceImpl(val repository: TaskRepository) : TaskService {
    
    override fun create(task: Task): Task {
        try {
            return repository.save(task)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun update(task: Task): Task {
        try {
            var taskFound = findById(task.id!!)

            taskFound.title = task.title
            taskFound.description = task.description
            taskFound.status = task.status
            taskFound.maximumDeliveryDate = task.maximumDeliveryDate

            return repository.save(taskFound)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun findById(id: UUID): Task {
        try {
            val taskFound = repository.findById(id)

            if (!taskFound.isPresent) {
                throw Exception("Task not found")
            }

            return taskFound.get()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun findAll(): List<Task> {
        try {
            return repository.findAll()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun deleteById(id: UUID) {
        try {
            val taskFound = findById(id)
            repository.deleteById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun changeStatus(id: UUID, newStatus: StatusTask): Task {
        try {
            var taskFound = findById(id)

            taskFound.status = newStatus

            return repository.save(taskFound)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun findAllLateTasks(): List<Task> {
        try {
            return repository.findAllByMaximumDeliveryDateLessThanAndStatusIsNot(LocalDate.now(), StatusTask.DONE)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}