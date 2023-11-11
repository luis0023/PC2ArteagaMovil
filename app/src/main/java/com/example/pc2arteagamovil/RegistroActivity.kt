package com.example.pc2arteagamovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pc2arteagamovil.model.EquipoModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre:EditText=findViewById(R.id.etNombre)
        val etAño:EditText=findViewById(R.id.etAño)
        val etTitulo:EditText=findViewById(R.id.etTitulos)
        val etUrlImg:EditText=findViewById(R.id.etUrlImg)
        val btnSave:Button=findViewById(R.id.btnGuardar)
        val btnVer:Button=findViewById(R.id.btnVer)
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")

        btnSave.setOnClickListener {
            val nombre=etNombre.text.toString()
            val año=etAño.text.toString()
            val titulos=etTitulo.text.toString()
            val url_img=etUrlImg.text.toString()
            val equipoModel=EquipoModel(nombre,año,titulos,url_img)

            db.collection("equipo")
                .add(equipoModel)
                .addOnSuccessListener { documentReference ->
                    Snackbar
                        .make(
                            findViewById(android.R.id.content)
                            ,"REGISTRO DEL EQUIPO EXITOS"
                            , Snackbar.LENGTH_LONG
                        ).show()
                }
                .addOnFailureListener { e ->
                    println("Error al agregar el EQUIPO: $e")
                }

        }

        btnVer.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)


        }
    }
}