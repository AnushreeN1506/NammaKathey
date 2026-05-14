package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView

    private lateinit var questionCounter: TextView

    private lateinit var submitButton: Button

    // OPTION CARDS

    private lateinit var optionCard1: LinearLayout
    private lateinit var optionCard2: LinearLayout
    private lateinit var optionCard3: LinearLayout
    private lateinit var optionCard4: LinearLayout

    // OPTION TEXTS

    private lateinit var optionText1: TextView
    private lateinit var optionText2: TextView
    private lateinit var optionText3: TextView
    private lateinit var optionText4: TextView

    private var selectedIndex = -1

    private var currentQuestionIndex = 0

    private var score = 0

    private lateinit var quizList: List<Quiz>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz)

        // VIEWS

        questionText =
            findViewById(R.id.questionText)

        questionCounter =
            findViewById(R.id.questionCounter)

        submitButton =
            findViewById(R.id.submitButton)

        // OPTION CARDS

        optionCard1 =
            findViewById(R.id.optionCard1)

        optionCard2 =
            findViewById(R.id.optionCard2)

        optionCard3 =
            findViewById(R.id.optionCard3)

        optionCard4 =
            findViewById(R.id.optionCard4)

        // OPTION TEXTS

        optionText1 =
            findViewById(R.id.optionText1)

        optionText2 =
            findViewById(R.id.optionText2)

        optionText3 =
            findViewById(R.id.optionText3)

        optionText4 =
            findViewById(R.id.optionText4)

        // HERO NAME

        val heroName =
            intent.getStringExtra("heroName")

        // LOAD DATA

        val districtList =
            JsonUtils.loadDistricts(this)

        var selectedHero: Hero? = null

        for (district in districtList) {

            for (hero in district.heroes) {

                if (hero.name == heroName) {

                    selectedHero = hero
                }
            }
        }

        quizList =
            selectedHero?.quiz ?: emptyList()

        // LOAD FIRST QUESTION

        loadQuestion()

        // OPTION CLICK EVENTS

        optionCard1.setOnClickListener {
            selectOption(0)
        }

        optionCard2.setOnClickListener {
            selectOption(1)
        }

        optionCard3.setOnClickListener {
            selectOption(2)
        }

        optionCard4.setOnClickListener {
            selectOption(3)
        }

        // SUBMIT BUTTON

        submitButton.setOnClickListener {

            if (selectedIndex == -1) {

                Toast.makeText(
                    this,
                    "Please select an answer",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            checkAnswer(heroName)
        }
    }

    private fun loadQuestion() {

        selectedIndex = -1

        resetOptionBackgrounds()

        val currentQuiz =
            quizList[currentQuestionIndex]

        questionCounter.text =
            "Question ${currentQuestionIndex + 1} of ${quizList.size}"

        questionText.text =
            currentQuiz.question

        optionText1.text =
            currentQuiz.options[0]

        optionText2.text =
            currentQuiz.options[1]

        optionText3.text =
            currentQuiz.options[2]

        optionText4.text =
            currentQuiz.options[3]
    }

    private fun selectOption(index: Int) {

        selectedIndex = index

        resetOptionBackgrounds()

        when (index) {

            0 -> optionCard1.setBackgroundResource(
                R.drawable.option_card_selected
            )

            1 -> optionCard2.setBackgroundResource(
                R.drawable.option_card_selected
            )

            2 -> optionCard3.setBackgroundResource(
                R.drawable.option_card_selected
            )

            3 -> optionCard4.setBackgroundResource(
                R.drawable.option_card_selected
            )
        }
    }

    private fun resetOptionBackgrounds() {

        optionCard1.setBackgroundResource(
            R.drawable.option_card_default
        )

        optionCard2.setBackgroundResource(
            R.drawable.option_card_default
        )

        optionCard3.setBackgroundResource(
            R.drawable.option_card_default
        )

        optionCard4.setBackgroundResource(
            R.drawable.option_card_default
        )
    }

    private fun checkAnswer(heroName: String?) {

        val correctIndex =
            quizList[currentQuestionIndex]
                .correctIndex

        if (selectedIndex == correctIndex) {

            score++

            Toast.makeText(
                this,
                "Correct Answer ✅",
                Toast.LENGTH_SHORT
            ).show()

        } else {

            Toast.makeText(
                this,
                "Wrong Answer ❌",
                Toast.LENGTH_SHORT
            ).show()
        }

        currentQuestionIndex++

        // NEXT QUESTION

        if (currentQuestionIndex < quizList.size) {

            loadQuestion()

        } else {

            // OPEN RESULT SCREEN

            val intent = Intent(
                this,
                ResultActivity::class.java
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
                quizList.size
            )

            startActivity(intent)

            finish()
        }
    }
}