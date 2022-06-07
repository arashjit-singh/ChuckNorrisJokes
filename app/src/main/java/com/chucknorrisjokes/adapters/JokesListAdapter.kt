package com.chucknorrisjokes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.chucknorrisjokes.databinding.ItemJokeRowItemBinding
import com.chucknorrisjokes.model.JokeDataClass

class JokesListAdapter(private var jokeList: List<JokeDataClass>) :
    RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    var callback: ItemClick? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val binding: ItemJokeRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mJoke: JokeDataClass) {
            val mParam: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            binding.dataClass = mJoke
            binding.parent.layoutParams = mParam
            binding.executePendingBindings()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeRowItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.clickListener = callback
        viewHolder.bind(jokeList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = jokeList.size

    fun updateData(list: List<JokeDataClass>?) {
        this.jokeList = list!!
    }


    interface ItemClick {
        fun onItemClick(joke: JokeDataClass)
    }

    fun removeItemFromList(joke: JokeDataClass) {
        jokeList.toMutableList().apply {
            (jokeList as ArrayList).remove(joke)
            notifyDataSetChanged()
        }

    }
}
