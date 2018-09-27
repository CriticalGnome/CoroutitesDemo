package com.criticalgnome.coroutitesdemo.model

import java.io.Serializable

data class Comment(
        val postId: Long,
        val id: Long,
        val name: String,
        val email: String,
        val body: String
): Serializable