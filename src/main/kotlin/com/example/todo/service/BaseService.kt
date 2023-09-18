package com.example.todo.service

interface BaseService <T, ID> {
    fun create(t: T): T

    fun update(t: T): T

    fun findById(id: ID): T

    fun findAll(): List<T>

    fun deleteById(id: ID)
}