package com.mansi.jokesapplication.presentation.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mansi.jokesapplication.databinding.ItemJokeBinding
import com.mansi.jokesapplication.domain.model.Joke
import kotlin.properties.Delegates

class JokesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mJokeList: List<Joke> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(
            ItemJokeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val joke = getItem(position)
        (holder as JokeViewHolder).bind(joke)
    }

    override fun getItemCount(): Int = if (mJokeList.isNullOrEmpty()) 0 else mJokeList.size

    private fun getItem(position: Int): Joke = mJokeList[position]

    inner class JokeViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(jokes: Joke) {
            binding.apply {
                jokeTitleTextView.text = jokes.joke
                jokeBodyTextView.text = "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe."
            }
        }
    }

}