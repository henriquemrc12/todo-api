package com.example.todo.model

import com.example.todo.enums.StatusTask
import jakarta.persistence.*
import lombok.Data
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.*

@Data
@Entity
@Table(name = "tb_taks")
data class Task(@Id
                var id: UUID? = UUID.randomUUID(),
                var title: String?,
                var description: String?,
                @Enumerated
                var status: StatusTask,
                @Column(name = "maximum_delivery_date")
                var maximumDeliveryDate: LocalDate
)