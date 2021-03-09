package com.example.samachar.news

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samachar.databinding.ListItemSamacharBinding
import com.example.samachar.network.Samachar

class SamacharAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Samachar, SamacharAdapter.SamacharViewHolder>(DiffUtilCallback()) {

    class SamacharViewHolder(private var binding: ListItemSamacharBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(samachar: Samachar) {
            binding.samachar = samachar
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SamacharViewHolder {

        return SamacharViewHolder(
            ListItemSamacharBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SamacharViewHolder, position: Int) {

        val samachar = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(samachar)
        }
        holder.bind(samachar)
    }

    class OnClickListener(val clickListener: (samachar: Samachar) -> Unit) {
        fun onClick(samachar: Samachar) = clickListener(samachar)
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Samachar>() {
    override fun areItemsTheSame(oldItem: Samachar, newItem: Samachar): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Samachar, newItem: Samachar): Boolean {
        return oldItem.equals(newItem)
    }
}