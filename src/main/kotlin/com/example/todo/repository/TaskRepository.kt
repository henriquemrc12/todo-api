package com.example.todo.repository

import com.example.todo.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface TaskRepository : JpaRepository<Task, UUID> {

    fun findAllByMaximumDeliveryDateLessThan(date: LocalDate): List<Task>
}