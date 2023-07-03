package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    // Acesso a dados
    private val repository = GuestRepository.getInstance(application)

    private var guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private var _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }
    fun get(id: Int){
        guestModel.value = repository.get(id)
    }
    fun save(guest: GuestModel) {

        if( guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Inserção com Sucesso!"
            } else {
                _saveGuest.value = ""
            }
        }
        else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualização com Sucesso!"
            } else {
                _saveGuest.value = ""
            }
        }

    }

}