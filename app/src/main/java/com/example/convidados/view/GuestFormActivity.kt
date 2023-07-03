package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()

        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun loadData() {
//        val bundle = intent.extras
//        if (bundle != null) {
//            val guestid2 = bundle.getInt(DataBaseConstants.GUEST.ID)
//            guestid = guestid2
//            val guest = viewModel.guest
//            if (guest.value != null) {
//                binding.editName.setText(guest.value!!.name)
//                if (guest.value!!.presence) binding.radioPresent.isChecked = true else
//                    binding.radioAbsent.isChecked = true
//            }
//        }
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val id = guestId
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val model = GuestModel(id, name, presence)

            viewModel.save(model)

            //todo temp
            //finish()
        }
    }
}