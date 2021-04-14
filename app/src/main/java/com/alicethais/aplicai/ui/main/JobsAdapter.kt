package com.alicethais.aplicai.ui.main
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alicethais.aplicai.R
import com.alicethais.aplicai.models.Job
import kotlinx.android.synthetic.main.item_job.view.*

class JobsAdapter(
    private val context: Context,
    private val jobs: List<Job>,
    private inline val onClickItemJob: (job: Job) -> Unit): RecyclerView.Adapter<JobsAdapter.JobsAdapterViewHolder>() {

    inner class JobsAdapterViewHolder (private val layout_item_job: View): RecyclerView.ViewHolder(layout_item_job){
        infix fun bind(job: Job){
            with(layout_item_job){
                tvTitle.setText(job.title)
                tvDescription.setText(job.description)

                val datePost = "${context.getString(R.string.date_post)} ${job.date_post}"
                tvDate.setText(datePost)

                tvIsRemote.setText(
                    if (job.is_remote)
                        context.getText(R.string.remote_work)
                    else
                        context.getText(R.string.on_site_work)
                )

                llContainer.setOnClickListener { onClickItemJob(job) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JobsAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_job, parent, false))

    override fun getItemCount() = jobs.size

    override fun onBindViewHolder(holder: JobsAdapterViewHolder, position: Int) = holder bind jobs[position]
}