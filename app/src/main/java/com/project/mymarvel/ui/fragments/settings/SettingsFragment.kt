package com.project.mymarvel.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.mymarvel.common.utils.buildSettingsState
import com.project.mymarvel.databinding.FragmentSettingsBinding
import com.project.mymarvel.ui.adapters.LanguageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var settingsState: SettingsState
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instances()
    }

    private fun instances() {
        stateHolder()
        configureRecyclerView()
    }

    private fun stateHolder() {
        settingsState = buildSettingsState()
    }

    private fun configureRecyclerView() {
        binding.languageRecycler.adapter = LanguageAdapter(
            settingsState.setLanguages(),
            settingsState::onLanguageClick
        )
    }
}