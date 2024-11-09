package com.example.app_calendar

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent.DispatcherState
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

    private lateinit var layoutMain: ConstraintLayout
    private lateinit var barMain: ProgressBar
    private lateinit var cardMain: CardView


    val colores = listOf(
        Color.rgb(171,225,136),
        Color.rgb(247,239,153),
        Color.rgb(130,48,56),
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
        rotarBackground()
    }



    private fun rotarBackground() {
        CoroutineScope(Dispatchers.Main).launch {
            var colorIndex = 0
            var colorBar = 2
            var contBar = 25
            while (isActive) {
                layoutMain.setBackgroundColor(colores[colorIndex])
                barMain.progress = contBar
                barMain.progressTintList = android.content.res.ColorStateList.valueOf(colores[colorBar])
                cardMain.setCardBackgroundColor(android.content.res.ColorStateList.valueOf(colores[colorBar]))
                cardMain.alpha = 0.4F
                colorIndex = (colorIndex + 1) % colores.size
                colorBar = (colorBar + 1) % colores.size
                contBar += 25
                if (contBar > 100) {
                    contBar = 25
                }
//                when (contBar){
//                   25 ->
//                }


                delay(2000)
            }
        }
    }

    private fun initComponent() {
        layoutMain = findViewById<ConstraintLayout>(R.id.main)
        barMain = findViewById<ProgressBar>(R.id.progressBar)
        cardMain = findViewById<CardView>(R.id.cardView)
    }
}

