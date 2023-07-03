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
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.view.adapter.GuestsAdapter
import com.example.convidados.view.listener.OnGuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel
import com.example.convidados.viewmodel.PresentViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var presentViewModel: PresentViewModel

    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presentViewModel =
            ViewModelProvider(this).get(PresentViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        binding.recyclerPresentGuests.layoutManager = LinearLayoutManager(context)

        binding.recyclerPresentGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                presentViewModel.delete(id)
                presentViewModel.getAll()
            }

        }

        adapter.attachListener(listener)

        //allGuestsViewModel.getAll()

        observe()

        return binding.root
    }

    private fun observe() {
        presentViewModel.presentGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onResume() {
        super.onResume()
        //Toast.makeText(context, "Entrei", Toast.LENGTH_LONG).show()
        presentViewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}