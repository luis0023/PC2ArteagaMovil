package com.example.pc2arteagamovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2arteagamovil.Adapter.ListadoAdapter
import com.example.pc2arteagamovil.model.EquipoModel
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        val db = FirebaseFirestore.getInstance()
        var lstEquipos: List<EquipoModel>
        val rvEquipo: RecyclerView = findViewById(R.id.rvEquipos)

        val btnNuevo:Button=findViewById(R.id.btnNuevo)
        db.collection("equipo")
            .addSnapshotListener { snap, e ->
                if (e != null) {
                    Log.i("ERROR", "Ocurrió un error")
                    return@addSnapshotListener
                }

                lstEquipos = snap!!.documents.map { document ->
                    EquipoModel(
                        document["nombre"].toString(),
                        document["año"].toString(),
                        document["titulos"].toString(),
                        document["urlImg"].toString()
                    )
                }

                rvEquipo.adapter = ListadoAdapter(lstEquipos)
                rvEquipo.layoutManager = LinearLayoutManager(this)


            }
        btnNuevo.setOnClickListener {

            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}