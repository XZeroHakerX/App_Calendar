package com.example.app_calendar

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Resultado : AppCompatActivity() {
    //Declaramos componentes necesarios
    private lateinit var layoutMain: ConstraintLayout
    private lateinit var botonAtras: Button
    private lateinit var textoResult: TextView
    private lateinit var imagenResult: ImageView
    private lateinit var textoPoesiaResult: TextView

    //Lista de colores para hacer animaciones y variaciones de color
    val colores = listOf(
        Color.rgb(171,225,136),
        Color.rgb(247,239,153),
        Color.rgb(130,58,66),
        Color.rgb(64,121,140)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recuperamos los datos necesarios de la actividad anterior
        val intent = intent
        var estacion = intent.getStringExtra("estacion")
        initComponent()
        initListeners()
        comprobarFecha(estacion)

    }

    //Funcion para comprobar la fecha que viene de la actividad principal
    private fun comprobarFecha(estacion: String?) {

        if(estacion != null){

            //Comprobacion de estacion para cambiar los colores y textos:
            if(estacion.equals("Primavera")){
                textoPoesiaResult.setText(R.string.poesia_primavera)
                textoResult.setText(R.string.textoPrimavera)
                textoResult.setTextColor(getColor(R.color.white))
                botonAtras.setTextColor(getColor(R.color.white))
                textoPoesiaResult.setTextColor(getColor(R.color.white))
                botonAtras.setBackgroundColor(colores[2])
                textoPoesiaResult.setBackgroundColor(colores[2])
                imagenResult.setImageResource(R.drawable.primavera)
                layoutMain.setBackgroundColor(colores[0])


            }
            if(estacion.equals("Verano")){
                textoPoesiaResult.setText(R.string.poesia_verano)
                textoResult.setText(R.string.textoVerano)
                textoResult.setTextColor(getColor(R.color.white))
                botonAtras.setTextColor(getColor(R.color.white))
                botonAtras.setBackgroundColor(colores[3])
                textoPoesiaResult.setBackgroundColor(colores[3])
                textoPoesiaResult.setTextColor(getColor(R.color.white))
                imagenResult.setImageResource(R.drawable.verano)
                layoutMain.setBackgroundColor(colores[1])
            }
            if(estacion.equals("Otono")){
                textoPoesiaResult.setText(R.string.poesia_otono)
                textoResult.setText(R.string.textoOtono)
                textoResult.setTextColor(getColor(R.color.black))
                botonAtras.setTextColor(getColor(R.color.black))
                botonAtras.setBackgroundColor(colores[0])
                textoPoesiaResult.setBackgroundColor(colores[0])
                textoPoesiaResult.setTextColor(getColor(R.color.black))
                imagenResult.setImageResource(R.drawable.otono)
                layoutMain.setBackgroundColor(colores[2])
            }
            if(estacion.equals("Invierno")){
                textoPoesiaResult.setText(R.string.poesia_invierno)
                textoResult.setText(R.string.textoInvierno)
                textoResult.setTextColor(getColor(R.color.black))
                botonAtras.setTextColor(getColor(R.color.black))
                botonAtras.setBackgroundColor(colores[1])
                textoPoesiaResult.setBackgroundColor(colores[1])
                textoPoesiaResult.setTextColor(getColor(R.color.black))
                imagenResult.setImageResource(R.drawable.invierno)
                layoutMain.setBackgroundColor(colores[3])
            }
        }else{
            textoPoesiaResult.setText("Error")
            textoResult.setText("Error")
        }
    }

    //Añade los listeners a los componentes
    private fun initListeners() {

        botonAtras.setOnClickListener{
            finish()
        }
    }

    //Inicializa Componentes que tenemos en .xml
    private fun initComponent() {
        layoutMain = findViewById(R.id.main)
        botonAtras = findViewById(R.id.botonAtras)
        textoResult = findViewById(R.id.textoResultado)
        imagenResult = findViewById(R.id.imagenResultado)
        textoPoesiaResult = findViewById(R.id.textoPoesiaResult)


    }
}