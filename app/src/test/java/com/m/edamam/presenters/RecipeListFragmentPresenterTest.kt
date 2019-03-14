package com.m.edamam.presenters

import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.`RecipeListFragmentView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeListFragmentPresenterTest {

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState: `RecipeListFragmentView$$State`

    @InjectMocks
    @Spy
    private lateinit var presenter: RecipeListFragmentPresenter

    @Before
    fun setUp() {
        presenter.setViewState(mockViewState)
    }

    @Test
    fun updateAdapter() {
        //Arrange
        val hitList: List<Hit> = ArrayList()
        `when`(mockRepository.getRecipesByName(RECIPE_ID_1))
                .thenReturn(Single.just(hitList))

        //Act
        presenter.updateAdapter(RECIPE_ID_1)

        //Assert
        verify(mockViewState).submitListIntoAdapter(hitList)
    }

    @Test
    fun whenUpdateAdapterShowsError() {
        //Arrange
        val expectedError = Throwable()
        `when`(mockRepository.getRecipesByName(""))
                .thenReturn(Single.error(expectedError))
        //Act
        presenter.updateAdapter("")
        //Assert
        verify(mockViewState).showError(expectedError)
    }

    @Test
    fun loadNextElements() {
        //Arrange
        val expectedPage = 2
        val expectedQuery = "query"
        val expectedPageSize = 5
        val hitList: List<Hit> = ArrayList()
        `when`(mockRepository.getRecipesByName(expectedQuery, expectedPage, expectedPageSize))
                .thenReturn(Single.just(hitList))

        //Act
        presenter.loadNextElements(expectedPage, expectedQuery, expectedPageSize)

        //Assert
        verify(mockViewState).addElementsToAdapter(hitList)
    }

    @Test
    fun whenLoadNextElementsShowsError() {
        //Arrange
        val expectedPage = 2
        val expectedQuery = "0"
        val expectedPageSize = 5
        val expectedError = Throwable()
        `when`(mockRepository.getRecipesByName(expectedQuery, expectedPage, expectedPageSize))
                .thenReturn(Single.error(expectedError))

        //Act
        presenter.loadNextElements(expectedPage, expectedQuery, expectedPageSize)

        //Assert
        verify(mockViewState).showError(expectedError)
    }
}
