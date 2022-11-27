package com.project.mymarvel.ui.home

import android.os.Bundle
import android.util.Log
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
import com.project.mymarvel.domain.Event
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.ui.home.adapters.*
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
        //placeholders()
        stateHolder()
        observers()
        listenSnapOnHomeRecyclerView()
        prepareRecyclerView()
    }

    private fun stateHolder() {
        homeState = buildHomeState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) {
            if(vm.firstTime)
                binding.items = it.items
            binding.events = it.events
            binding.error = it.error?.let(homeState::errorToString)
        }
    }

    private fun listenSnapOnHomeRecyclerView() {
        binding.mainRecycler.attachSnapHelperWithListener(LinearSnapHelper(), SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, object: OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                vm.updateEvents(position)
                Log.d("snap", "$position")
            }
        } )
    }

    private fun prepareRecyclerView() {
        with(binding) {
            mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
            eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        }

    }

    private fun placeholders() {
        val mainRecycler = binding.mainRecycler
        mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        mainRecycler.adapter = HomeAdapter(
            listOf(
                Hero(
                    0,
                    "Captain",
                    "https://media.vandalsports.com/i/640x360/4-2021/2021427125442_1.jpg"
                ),
                Hero(
                    1,
                    "Wanda",
                    "https://static.wikia.nocookie.net/marveldatabase/images/c/c8/Wanda_Maximoff_%28Earth-199999%29_from_Doctor_Strange_in_the_Multiverse_of_Madness_Promo_001.jpg/revision/latest?cb=20220504145159",
                )
            )
        )

        val eventRecycler = binding.eventRecycler
        eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        eventRecycler.adapter = EventAdapter(
            listOf(
                Event(
                    0,
                    "https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2022/07/comic-con.png?fit=2304%2C1408&quality=50&strip=all&ssl=1",
                    "Comic con!",
                    "The SAN DIEGO COMIC CONVENTION (Comic-Con International) is a California Nonprofit Public Benefit Corporation organized for charitable purposes and dedicated to creating the general public’s awareness"
                ),
                Event(
                    1,
                    "https://i0.wp.com/www.habkorea.net/wp-content/uploads/2019/04/Official-Lightstick-of-Avengers.jpg?resize=800%2C1000&ssl=1",
                    "Avengers: Concert theme",
                    "s going to snap its fingers and half the world will disappear – into the cinema for three hours and two minutes of their lives. The movie is set to be released in Korean theaters on April 24, 2019."
                )
            )
        )
        binding.indicator.attachToRecyclerView(eventRecycler)
    }

}