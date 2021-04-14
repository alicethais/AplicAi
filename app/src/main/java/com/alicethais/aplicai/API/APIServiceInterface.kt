package com.alicethais.aplicai.API
import com.alicethais.aplicai.models.Job
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import io.reactivex.schedulers.Schedulers

interface APIServiceInterface {

    @GET("v2/b48fddea")
    fun fetchJobs(): Observable<List<Job>>

    companion object Factory {

        fun create(): APIServiceInterface {
            val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.mocki.io/")
                .build()


            return retrofit.create(APIServiceInterface::class.java)
        }
    }
}