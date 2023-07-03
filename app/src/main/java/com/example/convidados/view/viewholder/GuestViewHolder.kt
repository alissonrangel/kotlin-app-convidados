package com.example.convidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {

        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.buttonDelete.setOnClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
           true
        }

        bind.buttonUpdate.setOnClickListener {
            listener.onClick(guest.id)
        }
//        // Atribui valores
//        item.textName.text = guest.name
//
//        // Atribui eventos
//        item.textName.setOnClickListener {
//            listener.onClick(guest.id)
//        }
//
//        // Atribui eventos
//        item.textName.setOnLongClickListener {
//            AlertDialog.Builder(itemView.context)
//                .setTitle(R.string.remocao_convidado)
//                .setMessage(R.string.deseja_remover)
//                .setPositiveButton(R.string.remover) { dialog, which ->
//                    listener.onDelete(guest.id)
//                }
//                .setNeutralButton(R.string.cancelar, null)
//                .show()
//
//            true
//        }
    }
}