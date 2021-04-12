package com.kristiania.lecture11_codealong.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristiania.lecture11_codealong.datastorage.RaceDao
import com.kristiania.lecture11_codealong.datastorage.RaceDatabase
import com.kristiania.lecture11_codealong.datastorage.RecordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(context: Context) : ViewModel() {
    private var raceDao: RaceDao = RaceDatabase.get(context).getDao()
    private var liveRecords = MutableLiveData<List<RecordEntity>>()

    fun getRecords(): MutableLiveData<List<RecordEntity>> {

        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                raceDao.getAllRecords()
            }
            liveRecords.value = result
        }

        return liveRecords
    }
}