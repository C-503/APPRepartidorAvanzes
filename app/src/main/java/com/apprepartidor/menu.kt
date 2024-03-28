package com.apprepartidor

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.facebook.login.LoginManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.facebook.FacebookSdk;

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

@Suppress("DEPRECATION")
class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message", "Integración de FireBase completa")
        analytics.logEvent("InitScreen", bundle)


        // Setup
        val bundle1 = intent.extras
        val email = bundle1?.getString("email")
        val provider =bundle1?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Guardar datos Google
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()


    }
    private fun setup(email: String, provider: String){
        title = "Inicio"
        val emailText = findViewById<TextView>(R.id.textView12)
        val providerText = findViewById<TextView>(R.id.textView13)
        val cerrar = findViewById<TextView>(R.id.button4)

        emailText.text = email
        providerText.text = provider

        cerrar.setOnClickListener{

            // Borrar Datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            if(provider == ProviderType.FACEBOOK.name){
                LoginManager.getInstance().logOut()
            }
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}