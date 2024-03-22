package com.apprepartidor

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

@Suppress("DEPRECATION")
class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integración de FireBase completa")
        analytics.logEvent("InitScreen", bundle)

        val bundle1 = intent.extras
        val email = bundle1?.getString("email")
        val provider =bundle1?.getString("provider")
        setup(email ?: "", provider ?: "")

    }
    private fun setup(email: String, provider: String){
        title = "Inicio"
        val emailText = findViewById<TextView>(R.id.textView12)
        val providerText = findViewById<TextView>(R.id.textView13)
        val cerrar = findViewById<TextView>(R.id.button4)

        emailText.text = email
        providerText.text = provider

        cerrar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}