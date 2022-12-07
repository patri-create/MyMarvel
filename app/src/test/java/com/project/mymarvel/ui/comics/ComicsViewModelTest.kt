package com.project.mymarvel.ui.comics

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import com.project.mymarvel.domain.Comic
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.Event
import com.project.mymarvel.rules.CoroutinesTestRule
import com.project.mymarvel.testsamples.sampleComic
import com.project.mymarvel.testsamples.sampleEvent
import com.project.mymarvel.ui.fragments.comics.ComicsViewModel
import com.project.mymarvel.ui.fragments.comics.ComicsViewModel.UiState
import com.project.mymarvel.usecases.FindComicsUseCase
import com.project.mymarvel.usecases.FindEventsByComicIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
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
class ComicsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    /* Mocks */
    @Mock
    lateinit var findComicsUseCase: FindComicsUseCase

    @Mock
    lateinit var findEventsByComicIdUseCase: FindEventsByComicIdUseCase

    /* Vars */
    private lateinit var vm: ComicsViewModel
    private val comics = listOf(sampleComic)
    private val events = listOf(sampleEvent.copy(id = 1))

    @Before
    fun setUp() {
        val resultComics: Either<Error, List<Comic>> = comics.right()
        runTest { whenever(findComicsUseCase()).thenReturn(resultComics) }
        vm = ComicsViewModel(findComicsUseCase, findEventsByComicIdUseCase)
    }

    @Test
    fun `State init updating`() = runTest {
        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(items = comics), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Reload comics when come from menu`() = runTest {
        vm.reload()
        runCurrent()

        vm.state.test {
            Assert.assertEquals(UiState(items = comics), awaitItem())
            cancel()
        }
    }

    @Test
    fun `State events updating`() = runTest {
        vm.items = comics
        val resultEvents: Either<Error, List<Event>> = events.right()
        whenever(findEventsByComicIdUseCase(0)).thenReturn(resultEvents)

        vm.updateEvents(0)

        vm.state.test() {
            awaitItem()
            Assert.assertEquals(UiState(items = comics), awaitItem())
            Assert.assertEquals(UiState(items = null, events = events), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Verify findEvents is called when updateEvent list`() = runTest {
        vm.items = comics
        val resultEvents: Either<Error, List<Event>> = events.right()
        whenever(findEventsByComicIdUseCase(0)).thenReturn(resultEvents)

        vm.updateEvents(0)
        runCurrent()

        verify(findEventsByComicIdUseCase).invoke(0)
    }
}