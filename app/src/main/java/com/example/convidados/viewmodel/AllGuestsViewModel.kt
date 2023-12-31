package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>().apply {}
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
         listAllGuests.value = repository.getAll()
    }

    fun getPresent(){
        listAllGuests.value = repository.getPresent()
    }

    fun getAbsent(){
        listAllGuests.value = repository.getAbsent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}