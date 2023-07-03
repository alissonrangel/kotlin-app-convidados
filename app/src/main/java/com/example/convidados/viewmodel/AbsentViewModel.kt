package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class AbsentViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is absent Fragment"
    }
    val text: LiveData<String> = _text

    private val repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val listAbsentGuests = MutableLiveData<List<GuestModel>>().apply {}
    val absentGuests: LiveData<List<GuestModel>> = listAbsentGuests

    fun getAll(){
        listAbsentGuests.value = repository.getAbsent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}