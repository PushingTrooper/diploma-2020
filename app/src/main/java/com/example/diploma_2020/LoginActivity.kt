package com.example.diploma_2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

const val USERNAME = "username"
const val EMAIL = "email"

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailField)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordField)
        val email = findViewById<TextInputEditText>(R.id.emailText)
        val password = findViewById<TextInputEditText>(R.id.passwordText)

        loginButton.setOnClickListener {
            if(!email.text.isNullOrEmpty() && !password.text.isNullOrEmpty()) {
                viewModel.login(email.text.toString(), password.text.toString())

                viewModel.user.observe(this, Observer {
                    if(it.email.isNotEmpty() && it.username.isNotEmpty()){
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra(USERNAME, it.username)
                            putExtra(EMAIL, it.email)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Something's wrong", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }
}