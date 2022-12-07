package com.project.mymarvel.ui.fragments.settings

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.project.mymarvel.R
import com.project.mymarvel.common.LocaleManager
import com.project.mymarvel.common.utils.toLanguage
import com.project.mymarvel.databinding.FragmentSettingsBinding
import com.project.mymarvel.databinding.ItemLanguageBinding
import com.project.mymarvel.domain.LanguageItem
import com.project.mymarvel.ui.adapters.LanguageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

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
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.languageRecycler.adapter = LanguageAdapter(setLanguages(), ::onLanguageClick)
    }

    private fun loadLanguage() {
        requireActivity().recreate()
    }

    private fun onLanguageClick(bind: ItemLanguageBinding, item: LanguageItem) {
        bind.imageView.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.language_shadow)
        bind.titleText.setTypeface(null, Typeface.BOLD)
        LocaleManager.newInstance(requireContext()).setNewLocale(item.locale)
        loadLanguage()
    }

    private fun setLanguages(): List<LanguageItem> {
        return listOf(
            LocaleManager.ENGLISH.toLanguage(),
            LocaleManager.SPANISH.toLanguage()
        )
    }
}