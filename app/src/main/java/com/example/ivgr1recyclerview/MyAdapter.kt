package com.example.ivgr1recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private val sweetsList: List<Sweets>,
    private val clickListener: (Sweets) -> Unit,
) : RecyclerView.Adapter<MyAdapter.SweetsViewHolder>() {

    class SweetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sweetName: TextView = itemView.findViewById(R.id.sweet_name)
        val sweetPrice: TextView = itemView.findViewById(R.id.sweet_price)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favorite_imagebutton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SweetsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item, parent, false)

        return SweetsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SweetsViewHolder, position: Int) {
        val currentSweet = sweetsList[position]
        holder.sweetName.text = currentSweet.name
        holder.sweetPrice.text = "Cena ${currentSweet.price} PLN"

        if(currentSweet.isFavorite){
            holder.favoriteButton.setImageResource(R.drawable.icon_favorite_24)
        } else {
            holder.favoriteButton.setImageResource(R.drawable.icon_favorite_border_24)
        }

        holder.favoriteButton.setOnClickListener {
            currentSweet.isFavorite = !currentSweet.isFavorite

            notifyItemChanged(position)
            if(currentSweet.isFavorite){
                Log.i("fa", "${currentSweet.name} dodano do ulubionych")
            } else {
                Log.i("fa", "${currentSweet.name} usunięto z ulubionych")
            }

        }

        holder.itemView.setOnClickListener {
            clickListener(currentSweet)
        }

        holder.addToCartButton.setOnClickListener {
            Log.i("add", "dodano do koszyka ${currentSweet.name}")
        }
    }

    override fun getItemCount(): Int = sweetsList.size

}