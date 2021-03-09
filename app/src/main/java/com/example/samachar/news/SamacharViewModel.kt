package com.example.samachar.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samachar.network.Samachar
import com.example.samachar.network.SamacharApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class SamacharStatus { LOADING, DONE, ERROR }

class SamacharViewModel : ViewModel() {

    private val _status = MutableLiveData<SamacharStatus>()
    val status: LiveData<SamacharStatus>
        get() = _status

    private val _samachars = MutableLiveData<List<Samachar>>()
    val samachars: LiveData<List<Samachar>>
        get() = _samachars

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getSamachar("in", "your_API_Key")
    }

    fun getSamachar(country: String, apiKey: String) {

        coroutineScope.launch {

            var getModelInstance = SamacharApi.retrofitService.getSamachar(country, apiKey)

            try {

                _status.value = SamacharStatus.LOADING
                var instance = getModelInstance.await()
                var listResult = instance.articles
                _status.value = SamacharStatus.DONE

                if (listResult.isNotEmpty()) {
                    _samachars.value = listResult
                }

            } catch (e: Exception) {
                _status.value = SamacharStatus.ERROR
                _samachars.value = ArrayList()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}