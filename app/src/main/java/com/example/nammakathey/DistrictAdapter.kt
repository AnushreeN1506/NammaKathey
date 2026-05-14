package com.example.nammakathey

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DistrictAdapter(
    private val districtList: List<District>
) : RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {

    class DistrictViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val districtName: TextView =
            itemView.findViewById(R.id.districtName)

        val districtTagline: TextView =
            itemView.findViewById(R.id.districtTagline)

        val heroCount: TextView =
            itemView.findViewById(R.id.heroCount)

        val districtImage: ImageView =
            itemView.findViewById(R.id.districtImage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DistrictViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_district,
                parent,
                false
            )

        return DistrictViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DistrictViewHolder,
        position: Int
    ) {

        val district = districtList[position]

        // DISTRICT NAME

        holder.districtName.text =
            district.name

        // TAGLINE

        holder.districtTagline.text =
            getDistrictSubtitle(district.name)

        // HERO COUNT

        holder.heroCount.text =
            "${district.heroes.size} Legendary Heroes"

        // SAFE IMAGE LOADING

        try {

            when (district.name) {

                "Belagavi" ->
                    holder.districtImage.setImageResource(R.drawable.belagavi)

                "Mysuru" ->
                    holder.districtImage.setImageResource(R.drawable.mysuru)

                "Bengaluru" ->
                    holder.districtImage.setImageResource(R.drawable.bengaluru)

                "Chitradurga" ->
                    holder.districtImage.setImageResource(R.drawable.chitradurga)

                "Gadag" ->
                    holder.districtImage.setImageResource(R.drawable.gadag)

                "Udupi" ->
                    holder.districtImage.setImageResource(R.drawable.udupi)

                "Haveri" ->
                    holder.districtImage.setImageResource(R.drawable.haveri)

                "Bagalkote" ->
                    holder.districtImage.setImageResource(R.drawable.bagalkote)

                "Vijayanagara" ->
                    holder.districtImage.setImageResource(R.drawable.vijayanagara)

                "Kodagu" ->
                    holder.districtImage.setImageResource(R.drawable.kodagu)

                "Ballari" ->
                    holder.districtImage.setImageResource(R.drawable.ballari)

                "Chikkaballapur" ->
                    holder.districtImage.setImageResource(R.drawable.chikkaballapur)

                else ->
                    holder.districtImage.setImageResource(R.drawable.mysuru)
            }

        } catch (e: Exception) {

            holder.districtImage.setImageResource(
                R.drawable.mysuru
            )
        }

        // CARD CLICK

        holder.itemView.setOnClickListener {

            try {

                Log.d(
                    "DISTRICT_CLICK",
                    district.name
                )

                val intent = Intent(
                    holder.itemView.context,
                    HeroActivity::class.java
                )

                intent.putExtra(
                    "districtName",
                    district.name
                )

                holder.itemView.context
                    .startActivity(intent)

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {

        return districtList.size
    }

    // DISTRICT TAGLINES

    private fun getDistrictSubtitle(
        districtName: String
    ): String {

        return when (districtName) {

            "Mysuru" ->
                "Kings • Literature • Heritage"

            "Belagavi" ->
                "Freedom Fighters • Warriors"

            "Bengaluru" ->
                "Innovation • Legacy • Stories"

            "Chitradurga" ->
                "Forts • Courage • Legends"

            "Gadag" ->
                "Poetry • Culture • Wisdom"

            "Udupi" ->
                "Spirituality • Tradition • Devotion"

            "Haveri" ->
                "Saints • Music • Philosophy"

            "Bagalkote" ->
                "Culture • Equality • Heritage"

            "Vijayanagara" ->
                "Empire • Kings • Glory"

            "Kodagu" ->
                "Nature • Warriors • Coffee"

            "Ballari" ->
                "History • Empire • Courage"

            "Chikkaballapur" ->
                "Engineering • Vision • Inspiration"

            else ->
                "Stories • Heroes • Legacy"
        }
    }
}