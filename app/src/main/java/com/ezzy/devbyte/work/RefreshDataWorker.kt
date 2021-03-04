package com.ezzy.devbyte.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ezzy.devbyte.database.getDatabase
import com.ezzy.devbyte.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
CoroutineWorker(appContext, params){

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        var repository = VideosRepository(database)

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
}