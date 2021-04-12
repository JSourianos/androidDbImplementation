package com.kristiania.lecture11_codealong.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristiania.lecture11_codealong.datastorage.RaceDao
import com.kristiania.lecture11_codealong.datastorage.RaceDatabase
import com.kristiania.lecture11_codealong.datastorage.RecordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordViewModel(context: Context) : ViewModel() {
    private var raceDao: RaceDao = RaceDatabase.get(context).getDao()

    fun addRaceRecord(entity: RecordEntity) {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                //Async call to insert into db
                raceDao.insertRecord(entity)
            }
        }
    }
}