package com.ezzy.devbyte.network

import com.ezzy.devbyte.database.DatabaseVideo
import com.ezzy.devbyte.domain.models.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer (
    val videos: List<NetworkVideo>
)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
        val title: String,
        val description: String,
        val url: String,
        val updated: String,
        val thumbnail: String,
        val closedCaptions: String?
)

//convert network result to database objects
fun NetworkVideoContainer.asDomainModel() : List<Video> {
    return videos.map { networkVideo ->
        Video(
            title = networkVideo.title,
            description = networkVideo.description,
            url = networkVideo.url,
            updated = networkVideo.updated,
            thumbnail = networkVideo.thumbnail
        )
    }
}

fun NetworkVideoContainer.asDatabaseModel() : Array<DatabaseVideo> {
    return videos.map { networkVideo ->
        DatabaseVideo(
            title = networkVideo.title,
            description = networkVideo.description,
            url = networkVideo.url,
            updated = networkVideo.updated,
            thumbnail = networkVideo.thumbnail
        )
    }.toTypedArray()
}