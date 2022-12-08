package com.project.mymarvel.ui.fragments.network

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.project.mymarvel.R
import com.project.mymarvel.databinding.FragmentNetworkBinding


class NetworkFragment : Fragment() {

    private lateinit var binding: FragmentNetworkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instances()
    }

    private fun instances() {
        controlOnBackPress()
        formatText()
    }

    //Support multi-language format
    private fun formatText() {
        val text = getString(R.string.internet_connection_title)
        val start = text.indexOf(text.split(" ").last())
        binding.title.text = SpannableString(getString(R.string.internet_connection_title)).apply {
            setSpan( StyleSpan(Typeface.BOLD), start, text.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
    }

    private fun controlOnBackPress() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}