package com.apprepartidor

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.apprepartidor.databinding.ActivityRegistrarseBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class registrarse : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val binding : ActivityRegistrarseBinding by lazy {
        ActivityRegistrarseBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
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

        setup()
    }
    private fun setup(){

        title = "Autenticacion"

        val botonabrir2 = findViewById<TextView>(R.id.button3)
        val emailText = findViewById<TextView>(R.id.editTextTextEmailAddress)
        val passwordText = findViewById<TextView>(R.id.editTextTextPassword)

        botonabrir2.setOnClickListener{

            if (emailText.text.isNotEmpty() && passwordText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailText.text.toString(),
                        passwordText.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }


            }




        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){

        val intent = Intent(this, login::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)

        }
        startActivity(intent)
    }


}
