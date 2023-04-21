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

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var forgotEmailEditText : EditText
    private lateinit var resetPasswordButton : Button
    private lateinit var registeredAlreadyButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        init()
        listeners()
    }

    private fun listeners() {
        resetPasswordButton.setOnClickListener {
            val email = forgotEmailEditText.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Reset password link sent to your email address", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to reset password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        registeredAlreadyButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun init(){
        forgotEmailEditText = findViewById(R.id.forgotEmailEditText)
        resetPasswordButton = findViewById(R.id.resetPasswordButton)
        registeredAlreadyButton = findViewById(R.id.registeredAlreadyButton)
    }


}