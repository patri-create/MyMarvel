package com.project.mymarvel.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.mymarvel.R
import com.project.mymarvel.common.utils.attachSnapHelperWithListener
import com.project.mymarvel.common.utils.buildHomeState
import com.project.mymarvel.common.utils.launchAndCollect
import com.project.mymarvel.databinding.FragmentHomeBinding
import com.project.mymarvel.ui.adapters.MarginItemDecoration
import com.project.mymarvel.ui.adapters.MarvelAdapter
import com.project.mymarvel.ui.adapters.OnSnapPositionChangeListener
import com.project.mymarvel.ui.adapters.SnapOnScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var state: HomeState
    private val adapter = MarvelAdapter { state.onItemClick(it) }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instances()
    }

    private fun instances() {
        stateHolder()
        observers()
        prepareRecyclerView()
        fromMenu()
    }

    private fun stateHolder() {
        state = buildHomeState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) { uiState ->
            controlMarvelShimmer(uiState.loadingMarvel)
            controlEventShimmer(uiState.loadingEvent)
            uiState.items?.let { binding.items = it }
            binding.events = uiState.events
            binding.error = uiState.error?.let(state::errorToString)
        }

        listenRefresh()
        listenSnapOnHomeRecyclerView()
    }

    private fun listenRefresh() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                vm.loadMarvelItems()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun listenSnapOnHomeRecyclerView() {
        binding.mainRecycler.attachSnapHelperWithListener(
            LinearSnapHelper(),
            SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
            object : OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int) {
                    vm.updateEvents(position)
                }
            })
    }

    private fun prepareRecyclerView() {
        with(binding) {
            mainRecycler.adapter = adapter
            mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
            eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        }
    }

    private fun fromMenu() {
        vm.reload()
    }

    private fun controlMarvelShimmer(isLoading: Boolean) {
        with(binding) {
            loadingMarvel = isLoading
            when (isLoading) {
                true -> marvelShimmer.shimmerComponent.startShimmer()
                false -> marvelShimmer.shimmerComponent.stopShimmer()
            }
        }
    }

    private fun controlEventShimmer(isLoading: Boolean) {
        with(binding) {
            loadingEvent = isLoading
            when (isLoading) {
                true -> eventShimmer.shimmerComponent.startShimmer()
                false -> eventShimmer.shimmerComponent.stopShimmer()
            }
        }
    }
}