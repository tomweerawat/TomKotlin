package com.example.hotumit.tomkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.hotumit.tomkotlin.adapter.DataAdapter
import com.example.hotumit.tomkotlin.model.TomPoJo
import com.example.hotumit.tomkotlin.network.RequestInterface
import com.example.hotumit.tomkotlin.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit


import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class MainActivity : AppCompatActivity(), DataAdapter.Listener {

    private val TAG = MainActivity::class.java.simpleName


    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAndroidArrayList: ArrayList<TomPoJo>? = null

    private var mAdapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

        initRecyclerView()

        loadJSON()
    }

    private fun initRecyclerView() {

        rv_android_list.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_android_list.layoutManager = layoutManager
    }

    private fun loadJSON() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
        val requestInterface = Retrofit
                .Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RequestInterface::class.java)

        mCompositeDisposable?.add(requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }

    private fun handleResponse(androidList: List<TomPoJo>) {

        mAndroidArrayList = ArrayList(androidList)
        mAdapter = DataAdapter(mAndroidArrayList!!, this)

        rv_android_list.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(android: TomPoJo) {

        Toast.makeText(this, "${android.name} Clicked !", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
