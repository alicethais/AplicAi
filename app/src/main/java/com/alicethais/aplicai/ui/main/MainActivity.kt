package com.alicethais.aplicai.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alicethais.aplicai.R
import com.alicethais.aplicai.models.Job
import com.alicethais.aplicai.ui.jobDetail.ActivityJobDetail
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), JobsContract.View {

    private lateinit var controller: JobsContract.Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize(){
        controller = JobsController()
        loadJobs()

        srlJobs?.setOnRefreshListener{ loadJobs() }
    }

    override fun onDestroy() {
        super.onDestroy()

        controller.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        srlJobs?.isRefreshing = show
    }

    override fun showMessageError(messageError: String?) {
        showProgress(false)

        val errorMsg =
            if (messageError != null)
                "${getString(R.string.attention_we_had_error)} $messageError"
            else
                getString(R.string.attention_we_had_error)

        alert(errorMsg, getString(R.string.attention)){
            yesButton {
                loadJobs()
            }
            noButton {  }
        }.show()
    }

    override fun onLoadedDataSuccess(jobs: List<Job>) {
        rvJobs?.adapter = JobsAdapter(this, jobs, ::onClickItemJob)
        rvJobs?.layoutManager = LinearLayoutManager(this)

        showProgress(false)
    }

    override fun loadJobs() {
        showProgress(true)
        controller.fetchJobs(::onLoadedDataSuccess, ::showMessageError)
    }

    override fun onClickItemJob(job: Job){

        val intent = Intent(this, ActivityJobDetail::class.java)
        intent.putExtra("JobSelected", job)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_bottom_to_top, R.anim.fade_out_long)
    }


}