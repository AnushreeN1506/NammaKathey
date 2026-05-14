package com.example.nammakathey

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HeroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hero)

        // VIEWS

        val heroListView =
            findViewById<ListView>(R.id.heroListView)

        val titleText =
            findViewById<TextView>(R.id.heroTitle)

        // GET DISTRICT NAME

        val districtName =
            intent.getStringExtra("districtName") ?: ""

        // TITLE

        titleText.text =
            "$districtName Heroes"

        // LOAD JSON

        val districtList =
            JsonUtils.loadDistricts(this)

        // FIND SELECTED DISTRICT

        val selectedDistrict =
            districtList.find {
                it.name.trim() == districtName.trim()
            }

        // HERO LIST

        val heroList =
            selectedDistrict?.heroes ?: emptyList()

        // ADAPTER

        val adapter =
            HeroAdapter(
                heroList,
                this
            )

        heroListView.adapter = adapter

        // CLICK HERO

        heroListView.setOnItemClickListener {
                _, _, position, _ ->

            val selectedHero =
                heroList[position]

            val intent =
                Intent(
                    this,
                    StoryActivity::class.java
                )

            // HERO NAME

            intent.putExtra(
                "heroName",
                selectedHero.name
            )

            // HERO SUMMARY

            intent.putExtra(
                "heroSummary",
                selectedHero.summary
            )

            // STORY PAGES DATA

            val storyTexts = ArrayList<String>()
            val pageTitles = ArrayList<String>()
            val storyTextsKn = ArrayList<String>()
            val pageTitlesKn = ArrayList<String>()

            selectedHero.storyPages.forEach {
                storyTexts.add(it.text)
                pageTitles.add(it.pageTitle)
                storyTextsKn.add(it.textKn)
                pageTitlesKn.add(it.pageTitleKn)
            }

            // SEND DATA

            intent.putStringArrayListExtra("storyPages", storyTexts)
            intent.putStringArrayListExtra("pageTitles", pageTitles)
            intent.putStringArrayListExtra("storyPagesKn", storyTextsKn)
            intent.putStringArrayListExtra("pageTitlesKn", pageTitlesKn)

            startActivity(intent)
        }
    }
}