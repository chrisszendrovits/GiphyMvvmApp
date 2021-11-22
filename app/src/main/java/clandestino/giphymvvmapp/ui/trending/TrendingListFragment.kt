package clandestino.giphymvvmapp.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import clandestino.giphymvvmapp.databinding.TrendingListFragmentBinding
import clandestino.giphymvvmapp.ui.trending.adapter.TrendingListPagingDataAdapter
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import kotlinx.coroutines.launch

class TrendingListFragment: Fragment() {

    private lateinit var viewModel: TrendingListViewModel
    private lateinit var binding: TrendingListFragmentBinding
    private lateinit var pageAdapter: TrendingListPagingDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vm: TrendingListViewModel  by viewModels()
        binding = TrendingListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageAdapter = TrendingListPagingDataAdapter()
        binding.trendingListRecyclerView.adapter = pageAdapter
        binding.trendingListRecyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.trendingList.observe(viewLifecycleOwner, {
                pageAdapter.submitData(lifecycle, it)
            })
        }
    }
}