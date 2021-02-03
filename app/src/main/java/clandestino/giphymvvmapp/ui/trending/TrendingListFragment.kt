package clandestino.giphymvvmapp.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import clandestino.giphymvvmapp.databinding.TrendingListFragmentBinding
import clandestino.giphymvvmapp.ui.trending.adapter.TrendingListPageAdapter
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import kotlinx.android.synthetic.main.trending_list_fragment.*

class TrendingListFragment: Fragment() {

    private lateinit var viewModel: TrendingListViewModel
    private lateinit var pageAdapter: TrendingListPageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vm: TrendingListViewModel  by viewModels()
        val binding = TrendingListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageAdapter = TrendingListPageAdapter()
        trending_list_recycler_view.adapter = pageAdapter
        trending_list_recycler_view.layoutManager = LinearLayoutManager(context)

        viewModel.trendingList.observe(
            this.viewLifecycleOwner,
            { pageAdapter.submitList(it) }
        )
    }
}