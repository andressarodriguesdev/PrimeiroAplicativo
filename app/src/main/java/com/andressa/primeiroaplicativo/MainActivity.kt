package com.andressa.primeiroaplicativo

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edit_value)
        val buttonStart: Button = findViewById(R.id.btn_start)
        val buttonStop: Button = findViewById(R.id.btn_stop)
        val result: TextView = findViewById(R.id.txt_result)

        buttonStart.setOnClickListener{

            try{
                val number = editText.text.toString().toLong()
                timer = object : CountDownTimer(number* 60 * 1000, 1000){
                    override fun onTick(millisUntilFinished: Long) {

                        var seconds =millisUntilFinished/1000
                        var minutes = seconds/60
                        seconds = seconds % 60
                        result.text = String.format("%02d:%02d", minutes , seconds)

                    }

                    override fun onFinish() {
                        result.text = "O tempo acabou"
                    }

                }
            timer?.start()

            }catch (e: NumberFormatException){
                Toast.makeText(this, "Digite um número do campo do texto", Toast.LENGTH_LONG).show()
            }
        buttonStop.setOnClickListener{
         timer?.cancel()
          }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}