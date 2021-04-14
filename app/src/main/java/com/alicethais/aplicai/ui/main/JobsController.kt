package com.alicethais.aplicai.ui.main

import com.alicethais.aplicai.API.APIServiceInterface
import com.alicethais.aplicai.models.Job
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JobsController: JobsContract.Controller {

    private val subscriptions = CompositeDisposable()

    override fun fetchJobs(onLoaded: (List<Job>) -> Unit,
                           onMessageError: (messageError: String?) -> Unit){

        val disposable =
            APIServiceInterface
                .create()
                .fetchJobs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { onLoaded(it) },
                    { onMessageError(it?.message) }
                )

        subscriptions.add(disposable)

    }

    override fun unsubscribe() = subscriptions.clear()

}