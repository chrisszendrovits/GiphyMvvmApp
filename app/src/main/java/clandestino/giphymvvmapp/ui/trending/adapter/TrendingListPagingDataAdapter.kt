package clandestino.giphymvvmapp.ui.trending.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import clandestino.giphymvvmapp.databinding.TrendingItemCardViewBinding
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingItemViewModel

class TrendingListPagingDataAdapter :
    PagingDataAdapter<TrendingItemViewModel, RecyclerView.ViewHolder>(adapterDiffCallback) {

    companion object {
        val adapterDiffCallback = object : DiffUtil.ItemCallback<TrendingItemViewModel>() {
            override fun areItemsTheSame(oldItem: TrendingItemViewModel, newItem: TrendingItemViewModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TrendingItemViewModel, newItem: TrendingItemViewModel): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TrendingItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = getItem(position) as TrendingItemViewModel
        var viewHolder: TrendingItemViewHolder = holder as TrendingItemViewHolder
        viewHolder.bind(viewModel)
    }

    /**
     * view holder class
     */
    class TrendingItemViewHolder(private val binding: TrendingItemCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: TrendingItemViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        companion object {
            fun newInstance(parent: ViewGroup): TrendingItemViewHolder {
                var binding = TrendingItemCardViewBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                )
                return TrendingItemViewHolder(binding);
            }
        }
    }
}