package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // VIEWS

        val emailEditText =
            findViewById<EditText>(R.id.emailEditText)

        val passwordEditText =
            findViewById<EditText>(R.id.passwordEditText)

        val loginButton =
            findViewById<Button>(R.id.loginButton)

        val signupButton =
            findViewById<Button>(R.id.signupButton)

        // LOGIN

        loginButton.setOnClickListener {

            val email =
                emailEditText.text.toString()

            val password =
                passwordEditText.text.toString()

            val sharedPreferences =
                getSharedPreferences(
                    "USER_DATA",
                    MODE_PRIVATE
                )

            val savedEmail =
                sharedPreferences.getString(
                    "EMAIL",
                    ""
                )

            val savedPassword =
                sharedPreferences.getString(
                    "PASSWORD",
                    ""
                )

            if (
                email == savedEmail &&
                password == savedPassword
            ) {

                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(
                        this,
                        DashboardActivity::class.java
                    )

                startActivity(intent)

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid Email or Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // OPEN SIGNUP

        signupButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    SignupActivity::class.java
                )

            startActivity(intent)
        }
    }
}