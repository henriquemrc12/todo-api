package com.example.todo.service

import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import java.util.*

interface TaskService : BaseService<Task, UUID> {

    fun changeStatus(id: UUID, newStatus: StatusTask): Task

    fun findAllLateTasks(): List<Task>
}