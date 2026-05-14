package com.example.nammakathey

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_progress)

        // VIEWS

        val badgeCountText =
            findViewById<TextView>(R.id.badgeCountText)

        val completedHeroesText =
            findViewById<TextView>(R.id.completedHeroesText)

        val achievementStatus =
            findViewById<TextView>(R.id.achievementStatus)

        // SHARED PREFERENCES

        val sharedPreferences: SharedPreferences =
            getSharedPreferences(
                "NammaKatheyPrefs",
                MODE_PRIVATE
            )

        val badgeCount =
            sharedPreferences.getInt(
                "BADGE_COUNT",
                0
            )

        // UPDATE UI

        badgeCountText.text =
            "🏅 Badges Earned: $badgeCount"

        completedHeroesText.text =
            "📖 Heroes Completed: $badgeCount"

        if (badgeCount > 0) {

            achievementStatus.text =
                "Unlocked ✅"

        } else {

            achievementStatus.text =
                "Locked 🔒"
        }
    }
}