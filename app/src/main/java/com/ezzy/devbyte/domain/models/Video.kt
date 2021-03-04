package com.ezzy.devbyte.domain.models

import com.ezzy.devbyte.util.smartTruncate

data class Video(
    private val title: String,
    private val description: String,
    private val url: String,
    private val updated: String,
    private val thumbnail:String
) {
    val shortDescription: String
        get() = description.smartTruncate(200)
}
