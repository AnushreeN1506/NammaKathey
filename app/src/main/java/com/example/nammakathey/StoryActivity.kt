package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class StoryActivity :
    AppCompatActivity(),
    TextToSpeech.OnInitListener {

    private lateinit var textToSpeech: TextToSpeech

    private var currentPage = 0

    private var isSpeaking = false
    
    private var isKannada = false

    private lateinit var pageIndicator: TextView

    private lateinit var nextButton: Button

    private lateinit var previousButton: Button

    private lateinit var listenButton: Button
    
    private lateinit var languageToggle: Button

    private lateinit var storyPages: ArrayList<String>
    private lateinit var pageTitles: ArrayList<String>
    
    private lateinit var storyPagesKn: ArrayList<String>
    private lateinit var pageTitlesKn: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_story)

        textToSpeech = TextToSpeech(this, this)

        // GET DATA

        val heroName =
            intent.getStringExtra("heroName")

        val heroSummary =
            intent.getStringExtra("heroSummary")

        storyPages =
            intent.getStringArrayListExtra("storyPages")
                ?: arrayListOf()

        pageTitles =
            intent.getStringArrayListExtra("pageTitles")
                ?: arrayListOf()
        
        storyPagesKn =
            intent.getStringArrayListExtra("storyPagesKn")
                ?: arrayListOf()

        pageTitlesKn =
            intent.getStringArrayListExtra("pageTitlesKn")
                ?: arrayListOf()

        // VIEWS

        val heroNameText =
            findViewById<TextView>(R.id.heroNameText)

        val summaryText =
            findViewById<TextView>(R.id.summaryText)

        val storyTitleText =
            findViewById<TextView>(R.id.storyTitleText)

        pageIndicator =
            findViewById(R.id.pageIndicator)

        val storyText =
            findViewById<TextView>(R.id.storyText)

        listenButton =
            findViewById(R.id.listenButton)

        nextButton =
            findViewById(R.id.nextButton)

        previousButton =
            findViewById(R.id.previousButton)

        languageToggle =
            findViewById(R.id.languageToggle)

        val quizButton =
            findViewById<Button>(R.id.quizButton)

        // SET DATA

        heroNameText.text = heroName

        summaryText.text = heroSummary

        updateStoryPage(
            storyTitleText,
            storyText
        )

        // LANGUAGE TOGGLE
        languageToggle.setOnClickListener {
            
            // Check if Kannada content exists
            if (storyPagesKn.isEmpty() || storyPagesKn[0].isEmpty()) {
                Toast.makeText(this, "Kannada translation not available yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            isKannada = !isKannada
            
            if (isKannada) {
                languageToggle.text = "KN | EN"
                languageToggle.backgroundTintList = getColorStateList(android.R.color.holo_orange_dark)
            } else {
                languageToggle.text = "EN | KN"
                languageToggle.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            }
            
            // Stop TTS if speaking
            if (isSpeaking) {
                textToSpeech.stop()
                isSpeaking = false
                listenButton.text = "🔊 Listen Narration"
            }
            
            updateStoryPage(storyTitleText, storyText)
        }

        // LISTEN BUTTON

        listenButton.setOnClickListener {

            val textToRead = if (isKannada) storyPagesKn[currentPage] else storyPages[currentPage]

            if (!isSpeaking) {
                
                // Set TTS Language
                val result = if (isKannada) {
                    textToSpeech.setLanguage(Locale("kn", "IN"))
                } else {
                    textToSpeech.setLanguage(Locale.US)
                }
                
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Language not supported on this device", Toast.LENGTH_SHORT).show()
                }

                textToSpeech.speak(
                    textToRead,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )

                listenButton.text =
                    "⏹ Stop Narration"

                isSpeaking = true

            } else {

                textToSpeech.stop()

                listenButton.text =
                    "🔊 Listen Narration"

                isSpeaking = false
            }
        }

        // NEXT BUTTON

        nextButton.setOnClickListener {

            if (currentPage < storyPages.size - 1) {

                textToSpeech.stop()

                isSpeaking = false

                listenButton.text =
                    "🔊 Listen Narration"

                currentPage++

                updateStoryPage(
                    storyTitleText,
                    storyText
                )
            }
        }

        // PREVIOUS BUTTON

        previousButton.setOnClickListener {

            if (currentPage > 0) {

                textToSpeech.stop()

                isSpeaking = false

                listenButton.text =
                    "🔊 Listen Narration"

                currentPage--

                updateStoryPage(
                    storyTitleText,
                    storyText
                )
            }
        }

        // QUIZ BUTTON

        quizButton.setOnClickListener {

            val intent =
                Intent(
                    this,
                    QuizActivity::class.java
                )

            intent.putExtra(
                "heroName",
                heroName
            )

            startActivity(intent)
        }
    }

    private fun updateStoryPage(
        titleView: TextView,
        storyView: TextView
    ) {

        if (storyPages.isNotEmpty()) {

            // PAGE NUMBER
            val chapterText = if (isKannada) "ಅಧ್ಯಾಯ ${currentPage + 1}" else "Chapter ${currentPage + 1}"
            pageIndicator.text = chapterText

            // TITLE & STORY
            if (isKannada && storyPagesKn.isNotEmpty()) {
                titleView.text = pageTitlesKn[currentPage]
                storyView.text = storyPagesKn[currentPage]
            } else {
                titleView.text = pageTitles[currentPage]
                storyView.text = storyPages[currentPage]
            }

            // PREVIOUS BUTTON

            previousButton.visibility =
                if (currentPage == 0)
                    View.INVISIBLE
                else
                    View.VISIBLE

            // NEXT BUTTON

            nextButton.visibility =
                if (currentPage == storyPages.size - 1)
                    View.INVISIBLE
                else
                    View.VISIBLE
        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setSpeechRate(0.9f)
            textToSpeech.setPitch(1.0f)
        }
    }

    override fun onDestroy() {

        super.onDestroy()

        textToSpeech.stop()

        textToSpeech.shutdown()
    }
}