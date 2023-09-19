package com.example.todo.controller

import com.example.todo.dto.TaskDTO
import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import com.example.todo.service.TaskService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/task")
class TaskController(val service: TaskService) {

    @PostMapping
    fun create(@RequestBody @Valid taskDTO: TaskDTO): ResponseEntity<Task> {
        return ResponseEntity.ok(service.create(taskDTO.toModel()))
    }

    @PutMapping
    fun update(@RequestBody taskDTO: TaskDTO): ResponseEntity<Task> {
        return ResponseEntity.ok(service.update(taskDTO.toModel()))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Task>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<Task> {
        return ResponseEntity.ok(service.findById(id))
    }

    @GetMapping("/all/late")
    fun findAllLateTasks(): ResponseEntity<List<Task>> {
        return ResponseEntity.ok(service.findAllLateTasks())
    }

    @PatchMapping("/{id}/{newStatus}")
    fun updateStatus(@PathVariable id: UUID, @PathVariable newStatus: StatusTask): ResponseEntity<Task> {
        return ResponseEntity.ok(service.changeStatus(id, newStatus))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity.BodyBuilder {
        service.deleteById(id)
        return ResponseEntity.accepted()
    }

}