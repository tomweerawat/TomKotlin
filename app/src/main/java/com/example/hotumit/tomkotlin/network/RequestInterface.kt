package com.example.hotumit.tomkotlin.network

import com.example.hotumit.tomkotlin.model.Android
import com.example.hotumit.tomkotlin.model.ItemModel
import com.example.hotumit.tomkotlin.model.TomPoJo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST


interface RequestInterface{


   /* @GET("api/android")
    fun getData() : Observable<List<TomPoJo>>*/

    @GET(" menu.json")
    fun getData() : Observable<List<TomPoJo>>
}