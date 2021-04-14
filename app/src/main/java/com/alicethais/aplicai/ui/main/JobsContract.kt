package com.alicethais.aplicai.ui.main

import com.alicethais.aplicai.models.Job

interface JobsContract{

    interface View{
        //Show the progress bar.
        fun showProgress(show: Boolean)

        //Show the message error  in UI.
        fun showMessageError(messageError: String?)

        //Show the data loaded in UI.
        fun onLoadedDataSuccess(jobs: List<Job>)

        fun loadJobs()

        fun onClickItemJob(job: Job)
    }

    interface Controller{
        fun fetchJobs(onLoaded: (List<Job>) -> Unit,
                      onMessageError: (messageError: String?) -> Unit)

        fun unsubscribe()
    }

}