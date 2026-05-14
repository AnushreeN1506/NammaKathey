package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        // VIEWS

        val greetingText =
            findViewById<TextView>(R.id.greetingText)

        val exploreButton =
            findViewById<LinearLayout>(R.id.exploreButton)

        val achievementCard =
            findViewById<LinearLayout>(R.id.achievementCard)


        // USER DATA

        val sharedPreferences =
            getSharedPreferences(
                "USER_DATA",
                MODE_PRIVATE
            )

        val userName =
            sharedPreferences.getString(
                "NAME",
                "User"
            )

        greetingText.text = userName

        // EXPLORE DISTRICTS

        exploreButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    DistrictActivity::class.java
                )

            startActivity(intent)
        }

        // ACHIEVEMENTS

        achievementCard.setOnClickListener {

            val intent =
                Intent(
                    this,
                    ProgressActivity::class.java
                )

            startActivity(intent)
        }

        // LANGUAGE BUTTON

    }
}