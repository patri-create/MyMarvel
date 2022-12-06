package com.project.mymarvel.ui.home

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Event
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.rules.CoroutinesTestRule
import com.project.mymarvel.testsamples.sampleEvent
import com.project.mymarvel.testsamples.sampleHero
import com.project.mymarvel.ui.fragments.home.HomeViewModel
import com.project.mymarvel.ui.fragments.home.HomeViewModel.UiState
import com.project.mymarvel.usecases.FindEventsByHeroIdUseCase
import com.project.mymarvel.usecases.FindHeroesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    /* Mocks */
    @Mock
    lateinit var findHeroesUseCase: FindHeroesUseCase

    @Mock
    lateinit var findEventsByHeroIdUseCase: FindEventsByHeroIdUseCase

    /* Vars */
    private lateinit var vm: HomeViewModel
    private val heroes = listOf(sampleHero)
    private val events = listOf(sampleEvent.copy(id = 1))

    @Before
    fun setUp() {
        val resultHeroes: Either<Error, List<Hero>> = heroes.right()
        runTest { whenever(findHeroesUseCase()).thenReturn(resultHeroes) }
        vm = HomeViewModel(findHeroesUseCase, findEventsByHeroIdUseCase)
    }

    @Test
    fun `State init updating`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(items = heroes), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Reload heroes when come from menu`() = runTest {
        vm.reload()
        runCurrent()

        vm.state.test {
            assertEquals(UiState(items = heroes), awaitItem())
            cancel()
        }
    }

    @Test
    fun `State events updating`() = runTest {
        vm.items = heroes
        val resultEvents: Either<Error, List<Event>> = events.right()
        whenever(findEventsByHeroIdUseCase(0)).thenReturn(resultEvents)

        vm.updateEvents(0)

        vm.state.test() {
            awaitItem()
            assertEquals(UiState(items = heroes), awaitItem())
            assertEquals(UiState(items = null, events = events), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Verify findEvents is called when updateEvent list`() = runTest {
        vm.items = heroes
        val resultEvents: Either<Error, List<Event>> = events.right()
        whenever(findEventsByHeroIdUseCase(0)).thenReturn(resultEvents)

        vm.updateEvents(0)
        runCurrent()

        verify(findEventsByHeroIdUseCase).invoke(0)
    }
}