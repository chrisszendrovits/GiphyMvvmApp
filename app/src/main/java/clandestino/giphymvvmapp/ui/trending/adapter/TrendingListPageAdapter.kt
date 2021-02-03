package clandestino.giphymvvmapp.ui.trending.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingItemViewModel

class TrendingListPageAdapter: PagedListAdapter<TrendingItemViewModel, RecyclerView.ViewHolder>(adapterDiffCallback) {

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
}