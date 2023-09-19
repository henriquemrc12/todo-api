package com.example.todo.dto

import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.*

data class TaskDTO(
        var id: UUID?,
        @field:NotBlank(message = "title is required")
        var title: String?,
        @field:NotBlank(message = "description is required")
        var description: String?,
        @field:NotBlank(message = "status is required")
        var status: String?,
        var maximumDeliveryDate: LocalDate = LocalDate.now()
) {
    fun toModel(): Task {
        return Task(
                id = if (Objects.isNull(this.id)) UUID.randomUUID() else this.id,
                title = this.title,
                description = this.description,
                status = StatusTask.valueOf(this.status!!),
                maximumDeliveryDate = this.maximumDeliveryDate
        )
    }
}