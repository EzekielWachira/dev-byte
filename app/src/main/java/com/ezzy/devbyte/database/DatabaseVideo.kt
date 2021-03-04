package com.ezzy.devbyte.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezzy.devbyte.domain.models.Video

@Entity
data class DatabaseVideo(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

fun List<DatabaseVideo>.asDomainModel() : List<Video> {
    return map { databaseVideo ->
        Video(
            url = databaseVideo.url,
            updated = databaseVideo.updated,
            title = databaseVideo.title,
            description = databaseVideo.description,
            thumbnail = databaseVideo.thumbnail
        )
    }
}
