package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.FragmentAbsentBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.AbsentViewModel
import com.example.convidados.viewmodel.PresentViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var absentViewModel: AbsentViewModel

    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        absentViewModel =
            ViewModelProvider(this).get(AbsentViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        binding.recyclerAbsentGuests.layoutManager = LinearLayoutManager(context)

        binding.recyclerAbsentGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                absentViewModel.delete(id)
                absentViewModel.getAll()
            }

        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    private fun observe() {
        absentViewModel.absentGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onResume() {
        super.onResume()
        //Toast.makeText(context, "Entrei", Toast.LENGTH_LONG).show()
        absentViewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}