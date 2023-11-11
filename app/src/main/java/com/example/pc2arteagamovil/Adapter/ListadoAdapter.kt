package com.example.pc2arteagamovil.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2arteagamovil.R
import com.example.pc2arteagamovil.model.EquipoModel
import com.squareup.picasso.Picasso

class ListadoAdapter (private var lstEquipos: List<EquipoModel>): RecyclerView.Adapter<ListadoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTitulos: TextView = itemView.findViewById(R.id.tvTitulos)
        val tvAño: TextView = itemView.findViewById(R.id.tvAño)
        val img: ImageView=itemView.findViewById(R.id.ivEquipo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.itemequipo, parent, false))
    }

    override fun getItemCount(): Int {
        return lstEquipos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCourse = lstEquipos[position]
        holder.tvNombre.text = itemCourse.nombre
        holder.tvTitulos.text = itemCourse.titulos
        holder.tvAño.text = itemCourse.año

       Picasso.get().load(itemCourse.urlImg).into(holder.img)
    }
}