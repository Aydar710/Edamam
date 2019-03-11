package com.m.edamam.presenters

import com.m.edamam.RecipeListAdapter
import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.pojo.Hit
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.`RecipeListFragmentView$$State`
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.experimental.coroutineContext

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

        /*doReturn(Single.just(hitList))
                .`when`(mockRepository.getRecipesByName(RECIPE_ID_1))*/

        //Act
        presenter.updateAdapter(RECIPE_ID_1)

        //Assert
        verify(mockViewState).submitListIntoAdapter(hitList)
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

//        doReturn(Single.just(hitList))
//                .`when`(mockRepository.getRecipesByName(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))

        //Act
        presenter?.loadNextElements(expectedPage, expectedQuery, expectedPageSize)

        //Assert
        verify(mockViewState).addElementsToAdapter(hitList)
    }
}