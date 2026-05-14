package com.example.nammakathey

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HeroAdapter(
    private val heroList: List<Hero>,
    private val activity: HeroActivity
) : BaseAdapter() {

    override fun getCount(): Int {
        return heroList.size
    }

    override fun getItem(position: Int): Any {
        return heroList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val view =
            LayoutInflater.from(activity)
                .inflate(
                    R.layout.item_hero,
                    parent,
                    false
                )

        val hero =
            heroList[position]

        val heroName =
            view.findViewById<TextView>(
                R.id.heroName
            )

        val heroRole =
            view.findViewById<TextView>(
                R.id.heroRole
            )

        val heroEra =
            view.findViewById<TextView>(
                R.id.heroEra
            )

        heroName.text =
            hero.name

        heroRole.text =
            hero.role

        heroEra.text =
            hero.era

        view.setOnClickListener {

            val intent =
                Intent(
                    activity,
                    StoryActivity::class.java
                )

            intent.putExtra(
                "heroName",
                hero.name
            )

            intent.putExtra(
                "heroSummary",
                hero.summary
            )

            val storyTexts =
                ArrayList<String>()

            val pageTitles =
                ArrayList<String>()

            hero.storyPages.forEach {

                storyTexts.add(it.text)

                pageTitles.add(it.pageTitle)
            }

            intent.putStringArrayListExtra(
                "storyPages",
                storyTexts
            )

            intent.putStringArrayListExtra(
                "pageTitles",
                pageTitles
            )

            activity.startActivity(intent)
        }

        return view
    }
}