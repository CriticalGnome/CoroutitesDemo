package com.criticalgnome.coroutitesdemo.model

import java.io.Serializable

data class Todo(
        val userId: Long,
        val id: Long,
        val title: String,
        val completed: Boolean
): Serializable