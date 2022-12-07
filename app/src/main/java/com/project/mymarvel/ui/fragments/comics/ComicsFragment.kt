package com.project.mymarvel.ui.fragments.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.mymarvel.R
import com.project.mymarvel.common.utils.attachSnapHelperWithListener
import com.project.mymarvel.common.utils.buildComicsState
import com.project.mymarvel.common.utils.launchAndCollect
import com.project.mymarvel.databinding.FragmentComicsBinding
import com.project.mymarvel.ui.adapters.MarginItemDecoration
import com.project.mymarvel.ui.adapters.OnSnapPositionChangeListener
import com.project.mymarvel.ui.adapters.SnapOnScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private val vm: ComicsViewModel by viewModels()
    private lateinit var state: ComicsState

    private lateinit var binding: FragmentComicsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsBinding.inflate(inflater, container, false)
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
        fromMenu()
    }

    private fun stateHolder() {
        state = buildComicsState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) { uiState ->
            uiState.items?.let { binding.items = it }
            binding.events = uiState.events
            binding.error = uiState.error?.let(state::errorToString)
        }
    }


    private fun listenSnapOnHomeRecyclerView() {
        binding.mainRecycler.attachSnapHelperWithListener(
            LinearSnapHelper(),
            SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
            object :
                OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int) {
                    vm.updateEvents(position)
                }
            })
    }

    private fun prepareRecyclerView() {
        with(binding) {
            mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
            eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        }
    }

    private fun fromMenu() {
        vm.reload()
    }
}