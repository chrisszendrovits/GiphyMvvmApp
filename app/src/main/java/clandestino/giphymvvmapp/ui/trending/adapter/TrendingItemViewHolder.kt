package clandestino.giphymvvmapp.ui.trending.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import clandestino.giphymvvmapp.databinding.TrendingItemCardViewBinding
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingItemViewModel

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