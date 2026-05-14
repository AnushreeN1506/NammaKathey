package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BadgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_badge)

        // GET DATA

        val heroName =
            intent.getStringExtra("heroName")

        val score =
            intent.getIntExtra("score", 0)

        val total =
            intent.getIntExtra("total", 0)

        // SHARED PREFERENCES

        val sharedPreferences =
            getSharedPreferences(
                "NammaKatheyPrefs",
                MODE_PRIVATE
            )

        // CHECK IF HERO ALREADY UNLOCKED

        val alreadyUnlocked =
            sharedPreferences.getBoolean(
                heroName,
                false
            )

        // SAVE ONLY ONCE

        if (!alreadyUnlocked) {

            val currentBadges =
                sharedPreferences.getInt(
                    "BADGE_COUNT",
                    0
                )

            sharedPreferences.edit()
                .putBoolean(
                    heroName,
                    true
                )
                .putInt(
                    "BADGE_COUNT",
                    currentBadges + 1
                )
                .apply()
        }

        // VIEWS

        val badgeHero =
            findViewById<TextView>(R.id.badgeHero)

        val scoreText =
            findViewById<TextView>(R.id.scoreText)

        val messageText =
            findViewById<TextView>(R.id.messageText)

        val exploreButton =
            findViewById<Button>(R.id.exploreButton)

        val homeButton =
            findViewById<Button>(R.id.homeButton)

        val progressButton =
            findViewById<Button>(R.id.progressButton)

        // SET DATA

        badgeHero.text =
            heroName

        scoreText.text =
            "Perfect Score: $score/$total"

        messageText.text =
            "Congratulations!\n\n" +
                    "You mastered the story of $heroName " +
                    "and officially unlocked the Heritage Hero Badge.\n\n" +
                    "Continue exploring more legendary heroes of Karnataka."

        // EXPLORE HEROES

        exploreButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    DistrictActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)

            finish()
        }

        // GO HOME

        homeButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)

            finish()
        }

        // OPEN PROGRESS DASHBOARD

        progressButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    ProgressActivity::class.java
                )

            startActivity(intent)
        }
    }
}