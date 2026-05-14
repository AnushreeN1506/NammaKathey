package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        // VIEWS

        val nameEditText =
            findViewById<EditText>(R.id.nameEditText)

        val emailEditText =
            findViewById<EditText>(R.id.emailEditText)

        val passwordEditText =
            findViewById<EditText>(R.id.passwordEditText)

        val signupButton =
            findViewById<Button>(R.id.signupButton)

        // SIGNUP BUTTON

        signupButton.setOnClickListener {

            val name =
                nameEditText.text.toString()

            val email =
                emailEditText.text.toString()

            val password =
                passwordEditText.text.toString()

            // VALIDATION

            if (
                name.isEmpty() ||
                email.isEmpty() ||
                password.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // SAVE USER

            val sharedPreferences =
                getSharedPreferences(
                    "USER_DATA",
                    MODE_PRIVATE
                )

            sharedPreferences.edit()
                .putString(
                    "NAME",
                    name
                )
                .putString(
                    "EMAIL",
                    email
                )
                .putString(
                    "PASSWORD",
                    password
                )
                .apply()

            Toast.makeText(
                this,
                "Account Created Successfully",
                Toast.LENGTH_SHORT
            ).show()

            // OPEN LOGIN

            val intent =
                Intent(
                    this,
                    LoginActivity::class.java
                )

            startActivity(intent)

            finish()
        }
    }
}