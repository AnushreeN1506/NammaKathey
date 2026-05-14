package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        // DATA

        val heroName =
            intent.getStringExtra("heroName")

        val score =
            intent.getIntExtra("score", 0)

        val total =
            intent.getIntExtra("total", 0)

        // VIEWS

        val resultEmoji =
            findViewById<TextView>(R.id.resultEmoji)

        val resultTitle =
            findViewById<TextView>(R.id.resultTitle)

        val resultScore =
            findViewById<TextView>(R.id.resultScore)

        val resultMessage =
            findViewById<TextView>(R.id.resultMessage)

        val actionButton =
            findViewById<Button>(R.id.actionButton)

        // SCORE

        resultScore.text =
            "Score: $score/$total"

        // SUCCESS

        if (score == total) {

            resultEmoji.text = "🏆"

            resultTitle.text =
                "Hero Badge Unlocked!"

            resultMessage.text =
                "Amazing work!\n\n" +
                        "You mastered the story of $heroName and unlocked the Heritage Hero Badge."

            actionButton.text =
                "Claim Badge"

            actionButton.setOnClickListener {

                val intent =
                    Intent(
                        this,
                        BadgeActivity::class.java
                    )

                intent.putExtra(
                    "heroName",
                    heroName
                )

                intent.putExtra(
                    "score",
                    score
                )

                intent.putExtra(
                    "total",
                    total
                )

                startActivity(intent)

                finish()
            }

        } else {

            // FAILURE

            resultEmoji.text = "⚔️"

            resultTitle.text =
                "Challenge Failed"

            resultMessage.text =
                "You scored $score/$total.\n\n" +
                        "Score full marks to unlock the Heritage Hero Badge.\n\n" +
                        "Retry the challenge and prove your knowledge."

            actionButton.text =
                "Retry Challenge"

            actionButton.setOnClickListener {

                finish()
            }
        }
    }
}