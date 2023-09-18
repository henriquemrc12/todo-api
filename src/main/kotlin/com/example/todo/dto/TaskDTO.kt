package com.example.todo.dto

import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.util.*

data class TaskDTO(

        val id: UUID?,
        @field:NotNull("title is required")
        val title: String,
        @field:NotNull("description is required")
        val description: String,
        @field:NotNull("status is required")
        val status: StatusTask?,
        val maximumDeliveryDate: LocalDate = LocalDate.now()
) {
    fun toModel(): Task {
        return Task(
                id = if (Objects.isNull(this.id)) UUID.randomUUID() else this.id,
                title = this.title,
                description = this.description,
                status = this.status!!,
                maximumDeliveryDate = this.maximumDeliveryDate
        )
    }
}