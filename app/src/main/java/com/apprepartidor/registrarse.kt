package com.apprepartidor

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.apprepartidor.databinding.ActivityRegistrarseBinding

class registrarse : AppCompatActivity() {

    private val binding : ActivityRegistrarseBinding by lazy {
        ActivityRegistrarseBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val botonabrir = findViewById<TextView>(R.id.textView7)

        botonabrir.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        val botonabrir2 = findViewById<TextView>(R.id.button3)

        botonabrir2.setOnClickListener{
            val intent = Intent(this, ubicacion::class.java)
            startActivity(intent)
        }
    }
}