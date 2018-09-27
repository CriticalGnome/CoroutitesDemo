package com.criticalgnome.coroutitesdemo.model

import java.io.Serializable

data class Photo(
        val albumId: Long,
        val id: Long,
        val title: String,
        val url: String,
        val thumbnailUrl: String
): Serializable