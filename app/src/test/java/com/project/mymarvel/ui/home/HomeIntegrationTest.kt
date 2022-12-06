package com.project.mymarvel.ui.home

import app.cash.turbine.test
import com.project.mymarvel.ui.helpers.buildMarvelRepository
import com.project.mymarvel.rules.CoroutinesTestRule
import com.project.mymarvel.ui.fragments.home.HomeViewModel
import com.project.mymarvel.ui.fragments.home.HomeViewModel.UiState
import com.project.mymarvel.usecases.FindEventsByHeroIdUseCase
import com.project.mymarvel.usecases.FindHeroesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `find heroes loaded from remote source`() = runTest {
        val vm = buildViewModel()

        vm.state.test {
            assertEquals(UiState(), awaitItem())

            val heroes = awaitItem().items!!
            assertEquals(0, heroes[0].id)
            assertEquals(1, heroes[1].id)
            assertEquals(2, heroes[2].id)
            cancel()
        }
    }

    private fun buildViewModel(): HomeViewModel {
        val marvelRepository = buildMarvelRepository()
        val findHeroesUseCase = FindHeroesUseCase(marvelRepository)
        val findEventsByHeroIdUseCase = FindEventsByHeroIdUseCase(marvelRepository)
        return HomeViewModel(findHeroesUseCase, findEventsByHeroIdUseCase)
    }
}