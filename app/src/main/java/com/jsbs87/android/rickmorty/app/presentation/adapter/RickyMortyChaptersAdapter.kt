package com.jsbs87.android.rickmorty.app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.jsbs87.android.rickmorty.app.R
import com.jsbs87.android.rickmorty.app.domain.model.Character
import com.jsbs87.android.rickmorty.app.presentation.extension.loadUrl
import kotlinx.android.synthetic.main.item_character.view.*

class RickyMortyChaptersAdapter(val onClickItem: (Character) -> Unit) :
    RecyclerView.Adapter<RickyMortyChaptersAdapter.CharacterVH>() {

    private val items = mutableListOf<Character>()

    class CharacterVH(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<AppCompatImageView>(R.id.image_character)
        private val title = view.findViewById<AppCompatTextView>(R.id.title_character)
        private val subtitle = view.findViewById<AppCompatTextView>(R.id.subtitle_character)


        fun bind(item: Character) {
            title.text = item.name
            subtitle.text = item.status
            val photoUrl = item.image
            if (photoUrl.isNotEmpty()) {
                image.loadUrl(photoUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            .let {
                CharacterVH(it)
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(items[position])
        holder.itemView.container_item_character.setOnClickListener {
            onClickItem(items[position])
        }
    }

    @UiThread
    fun addAll(list: List<Character>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}