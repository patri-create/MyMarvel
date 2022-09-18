package com.project.mymarvel.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.mymarvel.R
import com.project.mymarvel.databinding.FragmentHomeBinding
import com.project.mymarvel.domain.Heroes
import com.project.mymarvel.domain.MarvelItem

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
        val recycler = binding.recycler
        recycler.adapter = HomeAdapter(
            listOf(
                Heroes(
                    "https://media.vandalsports.com/i/640x360/4-2021/2021427125442_1.jpg",
                    "Capitán"
                ),
                Heroes(
                    "https://static.wikia.nocookie.net/marveldatabase/images/c/c8/Wanda_Maximoff_%28Earth-199999%29_from_Doctor_Strange_in_the_Multiverse_of_Madness_Promo_001.jpg/revision/latest?cb=20220504145159",
                    "Wanda"
                )
            )
        )
    }

}