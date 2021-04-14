package com.alicethais.aplicai.ui.jobDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alicethais.aplicai.R
import com.alicethais.aplicai.models.Job
import kotlinx.android.synthetic.main.activity_job_detail.*
import kotlinx.android.synthetic.main.item_job.view.*
import org.jetbrains.anko.email

class ActivityJobDetail: AppCompatActivity(){

    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_job_detail)

        btnClose?.setOnClickListener { finish() }
        btnApply?.setOnClickListener {
            if (job != null)
                email(job!!.mail, "${getString(R.string.contact_about_work)} - ${job?.title}")
        }
        load()
    }


    private fun load(){
        job =  intent?.getSerializableExtra("JobSelected") as Job?
        if (job != null){

            tvTitle.setText(job?.title)
            tvRemoteWork.setText(
                if (job!!.is_remote)
                    getText(R.string.remote_work)
                else
                    getText(R.string.on_site_work)
            )

            val datePost = "${getString(R.string.date_post)} ${job?.date_post}"
            tvDatePost.setText(datePost)
            tvDescription.setText(job?.description)
            tvSalary.setText("R$ ${job?.salary.toString()}")
        }
    }
}