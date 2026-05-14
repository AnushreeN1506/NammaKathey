package com.example.nammakathey

import android.content.Context
import org.json.JSONObject

object JsonUtils {

    fun loadDistricts(
        context: Context
    ): List<District> {

        val districtList =
            mutableListOf<District>()

        val jsonString =
            context.assets
                .open("district_data.json")
                .bufferedReader()
                .use { it.readText() }

        val jsonObject =
            JSONObject(jsonString)

        val districtsArray =
            jsonObject.getJSONArray("districts")

        for (i in 0 until districtsArray.length()) {

            val districtObject =
                districtsArray.getJSONObject(i)

            val districtId =
                districtObject.optString("id", "")

            val districtName =
                districtObject.optString("name", "")

            val heroesArray =
                districtObject.optJSONArray("heroes")

            val heroList =
                mutableListOf<Hero>()

            if (heroesArray != null) {

                for (j in 0 until heroesArray.length()) {

                    val heroObject =
                        heroesArray.getJSONObject(j)

                    val heroId =
                        heroObject.optString("id", "")

                    val heroName =
                        heroObject.optString("name", "")

                    val heroEra =
                        heroObject.optString("era", "")

                    val heroRole =
                        heroObject.optString("role", "")

                    val heroSummary =
                        heroObject.optString("summary", "")

                    val imageRes =
                        heroObject.optString("image", "")

                    // VALUES

                    val valuesList =
                        mutableListOf<String>()

                    val valuesArray =
                        heroObject.optJSONArray("values")

                    if (valuesArray != null) {

                        for (k in 0 until valuesArray.length()) {

                            valuesList.add(
                                valuesArray.getString(k)
                            )
                        }
                    }

                    // STORY PAGES

                    val storyPageList =
                        mutableListOf<StoryPage>()

                    val storyPagesArray =
                        heroObject.optJSONArray("storyPages")

                    if (storyPagesArray != null) {

                        for (k in 0 until storyPagesArray.length()) {

                            val pageObject =
                                storyPagesArray.getJSONObject(k)

                            val page =
                                StoryPage(

                                    pageNumber =
                                        pageObject.optInt(
                                            "pageNumber",
                                            0
                                        ),

                                    pageTitle =
                                        pageObject.optString(
                                            "pageTitle",
                                            ""
                                        ),

                                    text =
                                        pageObject.optString(
                                            "text",
                                            ""
                                        ),
                                    
                                    pageTitleKn =
                                        pageObject.optString(
                                            "pageTitleKn",
                                            ""
                                        ),
                                    
                                    textKn = 
                                        pageObject.optString(
                                            "textKn",
                                            ""
                                        )
                                )

                            storyPageList.add(page)
                        }
                    }

                    // QUIZ

                    val quizList =
                        mutableListOf<Quiz>()

                    val quizArray =
                        heroObject.optJSONArray("quiz")

                    if (quizArray != null) {

                        for (k in 0 until quizArray.length()) {

                            val quizObject =
                                quizArray.getJSONObject(k)

                            val optionsList =
                                mutableListOf<String>()

                            val optionsArray =
                                quizObject.optJSONArray(
                                    "options"
                                )

                            if (optionsArray != null) {

                                for (m in 0 until optionsArray.length()) {

                                    optionsList.add(
                                        optionsArray.getString(m)
                                    )
                                }
                            }

                            val quiz =
                                Quiz(

                                    question =
                                        quizObject.optString(
                                            "question",
                                            ""
                                        ),

                                    options =
                                        optionsList,

                                    correctIndex =
                                        quizObject.optInt(
                                            "correctIndex",
                                            0
                                        )
                                )

                            quizList.add(quiz)
                        }
                    }

                    // CREATE HERO

                    val hero =
                        Hero(

                            id = heroId,

                            name = heroName,

                            era = heroEra,

                            role = heroRole,

                            imageRes = imageRes,

                            summary = heroSummary,

                            values = valuesList,

                            storyPages = storyPageList,

                            quiz = quizList
                        )

                    heroList.add(hero)
                }
            }

            // CREATE DISTRICT

            val district =
                District(

                    id = districtId,

                    name = districtName,

                    heroes = heroList
                )

            districtList.add(district)
        }

        return districtList
    }
}