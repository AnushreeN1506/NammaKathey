package com.example.nammakathey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DistrictActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_district)

        // RECYCLER VIEW

        val districtRecyclerView =
            findViewById<RecyclerView>(
                R.id.districtRecyclerView
            )

        // LOAD DISTRICTS

        val districtList =
            JsonUtils.loadDistricts(this)

        // SET LAYOUT MANAGER

        districtRecyclerView.layoutManager =
            LinearLayoutManager(this)

        // SET ADAPTER

        districtRecyclerView.adapter =
            DistrictAdapter(districtList)
    }
}