package com.project.mymarvel.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.mymarvel.R
import com.project.mymarvel.common.utils.attachSnapHelperWithListener
import com.project.mymarvel.common.utils.buildHomeState
import com.project.mymarvel.common.utils.launchAndCollect
import com.project.mymarvel.databinding.FragmentHomeBinding
import com.project.mymarvel.ui.adapters.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var homeState: HomeState

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        listenSnapOnHomeRecyclerView()
        prepareRecyclerView()
    }

    private fun stateHolder() {
        homeState = buildHomeState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) { state ->
            state.items?.let { binding.items = it }
            binding.events = state.events
            binding.error = state.error?.let(homeState::errorToString)
        }
    }

    private fun listenSnapOnHomeRecyclerView() {
        binding.mainRecycler.attachSnapHelperWithListener(LinearSnapHelper(), SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, object: OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                vm.updateEvents(position)
            }
        } )
    }

    private fun prepareRecyclerView() {
        with(binding) {
            mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
            eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        }
    }

    override fun onResume() {
        super.onResume()
        vm.onResume()
    }
}