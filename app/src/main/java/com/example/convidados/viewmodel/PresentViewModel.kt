package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is present Fragment"
    }
    val text: LiveData<String> = _text

    private val repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val listPresentGuests = MutableLiveData<List<GuestModel>>().apply {}
    val presentGuests: LiveData<List<GuestModel>> = listPresentGuests

    fun getAll(){
        listPresentGuests.value = repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}