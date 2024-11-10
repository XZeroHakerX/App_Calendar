package com.example.app_calendar

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.os.Bundle
import android.view.KeyEvent.DispatcherState
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DrawableUtils
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //Declaramos componentes necesarios
    private lateinit var layoutMain: ConstraintLayout
    private lateinit var barMain: ProgressBar
    private lateinit var cardMain: CardView
    private lateinit var imagenMenu: ImageView
    private lateinit var botonMenu: Button

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
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        initListener()
        rotarBackground()
    }

    private fun initListener() {
//Inicializamos el boton de seleccionar fecha y creamos una instancia de calendario para manejo de fecha
// y creamos un DatePickerDialog con la informacion necesaria para luego ejecutar .show()
        botonMenu.setOnClickListener(){
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                {_, anioSelect, mesSelect, diaSelect ->
                    val estacion = estacion(diaSelect, mesSelect)
                    pantallaResultado(estacion, anioSelect, mesSelect, diaSelect)
                },
                anio,mes,dia
            )
            datePicker.show()
        }

    }
//Funcion para la llamada de la siguiente actividad Resultado.
    private fun pantallaResultado(estacion: String, anio: Int, mes: Int, dia: Int ) {
        val intent = Intent(this,Resultado::class.java)
        intent.putExtra("estacion", estacion)
        intent.putExtra("anio", anio)
        intent.putExtra("mes", mes)
        intent.putExtra("dia", dia)
        startActivity(intent)

    }
//Funcion para comprobar que estacion es la fecha proporcionada.
    private fun estacion(dia: Int, mes: Int): String {

        if(mes == 0 || mes == 1 || mes == 11 && dia >= 21 || mes == 2 && dia <= 20)
            return "Invierno"

        if(mes == 3 || mes == 4 || mes == 2 || mes == 5 && dia <= 20)
            return "Primavera"

        if (mes == 6 || mes == 7 || mes == 5 || mes == 8 && dia <= 20)
            return "Verano"

        if (mes == 9 || mes == 10 || mes == 8 || mes == 11 )
            return "Otono"

        return "Desconocido"
    }

//Funcion con COroutine para hacer las transacciones de color de fondo y demas componentes.
    @SuppressLint("ResourceAsColor")
    private fun rotarBackground() {

        CoroutineScope(Dispatchers.Main).launch {
            var colorIndex = 0
            var colorBar = 2
            var contBar = 25
//Mientras la actividad esta activa, este bucle no terminará
            while (isActive) {
                layoutMain.setBackgroundColor(colores[colorIndex])

                barMain.progress = contBar
                barMain.progressTintList = android.content.res.ColorStateList.valueOf(colores[colorBar])

                cardMain.setCardBackgroundColor(android.content.res.ColorStateList.valueOf(colores[colorBar]))


                botonMenu.setBackgroundColor(colores[colorBar])

                colorIndex = (colorIndex + 1) % colores.size
                colorBar = (colorBar + 1) % colores.size

// Cambio de la progresion en la barra de progresos
                when (contBar){

                   25 -> {
                          imagenMenu.setImageResource(R.drawable.primavera)
                          botonMenu.setTextColor(getResources().getColor(R. color. white))
                          }
                   50 -> imagenMenu.setImageResource(R.drawable.verano)
                   75 -> {
                           imagenMenu.setImageResource(R.drawable.otono)
                           botonMenu.setTextColor(getResources().getColor(R. color. black))
                           }
                   100 -> imagenMenu.setImageResource(R.drawable.invierno)

                }
//Controlamos el contador de la barra
                contBar += 25

                if (contBar > 100) {
                    contBar = 25
                }
//Con Delay damos el tiempo de espera entre repeticion den milisegundos
                delay(2000)
            }
        }
    }

    //Inicializa Componentes que tenemos en .xml
    private fun initComponent() {
        layoutMain = findViewById<ConstraintLayout>(R.id.main)
        barMain = findViewById<ProgressBar>(R.id.progressBar)
        cardMain = findViewById<CardView>(R.id.cardViewImagenes)
        cardMain.alpha = 0.4F
        imagenMenu = findViewById<ImageView>(R.id.imagenMenu)
        botonMenu = findViewById<Button>(R.id.btnMenu)


    }
}

