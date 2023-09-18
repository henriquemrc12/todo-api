package com.example.todo.controller

import com.example.todo.dto.TaskDTO
import com.example.todo.enums.StatusTask
import com.example.todo.model.Task
import com.example.todo.service.TaskService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/task")
class TaskController(val service: TaskService) {

    @PostMapping
    fun create(@Valid @RequestBody taskDTO: TaskDTO): ResponseEntity<Task> {
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