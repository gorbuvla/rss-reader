package me.gorbuvla.core.model

import android.app.job.JobParameters
import android.app.job.JobService

/**
 * TODO add class description
 */
class DownloadDataService : JobService() {

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        return false
    }
}