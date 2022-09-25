package com.project.mymarvel.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import androidx.viewpager.widget.PagerAdapter
import com.project.mymarvel.R
import com.project.mymarvel.databinding.FragmentHomeBinding
import com.project.mymarvel.domain.Event
import com.project.mymarvel.domain.Hero

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

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
        val mainRecycler = binding.mainRecycler
        mainRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        mainRecycler.adapter = HomeAdapter(
            listOf(
                Hero(
                    "https://media.vandalsports.com/i/640x360/4-2021/2021427125442_1.jpg",
                    "Capitán"
                ),
                Hero(
                    "https://static.wikia.nocookie.net/marveldatabase/images/c/c8/Wanda_Maximoff_%28Earth-199999%29_from_Doctor_Strange_in_the_Multiverse_of_Madness_Promo_001.jpg/revision/latest?cb=20220504145159",
                    "Wanda"
                )
            )
        )

        val eventRecycler = binding.eventRecycler
        eventRecycler.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        eventRecycler.adapter = EventAdapter(
            listOf(
                Event(
                    "https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2022/07/comic-con.png?fit=2304%2C1408&quality=50&strip=all&ssl=1",
                    "Comic con!",
                    "The SAN DIEGO COMIC CONVENTION (Comic-Con International) is a California Nonprofit Public Benefit Corporation organized for charitable purposes and dedicated to creating the general public’s awareness"
                ),
                Event(
                    "https://i0.wp.com/www.habkorea.net/wp-content/uploads/2019/04/Official-Lightstick-of-Avengers.jpg?resize=800%2C1000&ssl=1",
                    "Avengers: Concert theme",
                    "s going to snap its fingers and half the world will disappear – into the cinema for three hours and two minutes of their lives. The movie is set to be released in Korean theaters on April 24, 2019."
                )
            )
        )
        binding.indicator.attachToRecyclerView(eventRecycler)
    }

}