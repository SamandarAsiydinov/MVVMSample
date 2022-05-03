package com.samsdk.mvvmexample.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsdk.mvvmexample.model.RecyclerList
import com.samsdk.mvvmexample.network.RetroInstance
import com.samsdk.mvvmexample.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RvViewModel: ViewModel() {

    lateinit var rvListData: MutableLiveData<RecyclerList>

    init {
        rvListData = MutableLiveData()
    }
    fun getList(): MutableLiveData<RecyclerList> {
        return rvListData
    }

    fun makeApiCall(query: String) {
        RetroInstance.getRetroInstance().create(RetroService::class.java)
            .getDataFromApi(query).enqueue(object : Callback<RecyclerList> {
                override fun onResponse(
                    call: Call<RecyclerList>,
                    response: Response<RecyclerList>
                ) {
                    if (response.isSuccessful) {
                       rvListData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                    Log.d("onFailure","${t.message}")
                    rvListData.postValue(null)
                }
            })
    }
}