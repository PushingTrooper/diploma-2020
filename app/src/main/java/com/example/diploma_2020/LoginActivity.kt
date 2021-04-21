package com.example.diploma_2020

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import com.example.diploma_2020.data.LoginResponse
import com.example.diploma_2020.helpers.GenericResponse
import com.example.diploma_2020.helpers.NetworkResponse
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

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
        val imageView = findViewById<ImageView>(R.id.imageView)
        val textView = findViewById<TextView>(R.id.textView)
        val snackbarView = findViewById<CoordinatorLayout>(R.id.snackbar_view)

        val rightNow = Calendar.getInstance()
        val currentHour =
            rightNow[Calendar.HOUR_OF_DAY] // return the hour in 24 hrs format (ranging from 0-23)

        if (currentHour in 7..17) {
            imageView.setImageResource(R.drawable.good_morning_img)
            textView.text = "Morning"
        } else {
            imageView.setImageResource(R.drawable.good_night_img)
            textView.text = "Night"
        }

        loginButton.setOnClickListener {
            if (!email.text.isNullOrEmpty() && !password.text.isNullOrEmpty()) {
                //email:email@email.com password:test
                viewModel.login(email.text.toString(), password.text.toString())
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Snackbar.make(
                                snackbarView,
                                "Something's wrong",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.code() == 200) {
                                if (response.body()!!.email.isNotEmpty() && response.body()!!.username.isNotEmpty()) {
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java).apply {
                                            putExtra(USERNAME, response.body()!!.username)
                                            putExtra(EMAIL, response.body()!!.email)
                                        }
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Snackbar.make(
                                        snackbarView,
                                        "Something's wrong",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                val jObjError = JSONObject(response.errorBody()!!.string())

                                Snackbar.make(
                                    snackbarView,
                                    jObjError["error"].toString(),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } else {
                Snackbar.make(
                    snackbarView,
                    "Ju duhet të plotësoni fushat",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    }
}