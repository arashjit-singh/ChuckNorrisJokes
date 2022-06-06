package com.chucknorrisjokes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.chucknorrisjokes.databinding.TextRowItemBinding

class CategoryListAdapter(private var categoryList: ArrayList<String>) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    var callback: ItemClick? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mName: String) {
            val mParam: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            binding.key = mName
            binding.parent.layoutParams = mParam
            binding.executePendingBindings()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextRowItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.itemClickListener = callback
        viewHolder.bind(categoryList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = categoryList.size

    interface ItemClick {
        fun onItemClick(category: String)
    }
}
