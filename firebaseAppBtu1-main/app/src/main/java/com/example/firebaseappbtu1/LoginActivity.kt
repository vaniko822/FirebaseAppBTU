package com.example.firebaseappbtu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmailEditText : EditText
    private lateinit var loginPasswordEditText : EditText
    private lateinit var loginButton : Button
    private lateinit var forgotPasswordButton : TextView
    private lateinit var notRegisteredButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        listeners()
    }

    private fun listeners() {

        loginButton.setOnClickListener {

            val email = loginEmailEditText.text.toString()
            val password = loginPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "The fields are empty and fill them", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
            finish()
        }

        notRegisteredButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

    }

    private fun init(){
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        notRegisteredButton = findViewById(R.id.notRegisteredButton)
    }
}