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

@RunWith(MockitoJUnitRunner::class)
class RecipeListFragmentPresenterTest {

    @InjectMocks
    @Spy
    private var presenter: RecipeListFragmentPresenter? = null

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState: `RecipeListFragmentView$$State`

    @Before
    fun setUp() {
        presenter?.setViewState(mockViewState)
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
        presenter?.updateAdapter(RECIPE_ID_1)

        //Assert
        verify(mockViewState).submitListIntoAdapter(hitList)
    }

    @Test
    fun loadNextElements() {
        //Arrange
        val hitList: List<Hit> = ArrayList()
        //`when`(mockRepository.getRecipesByName(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
        //        .thenReturn(Single.just(hitList))

        doReturn(Single.just(hitList))
                .`when`(mockRepository.getRecipesByName(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))

        //Act
        presenter?.loadNextElements(Mockito.anyInt(), Mockito.anyString(), Mockito.anyInt())

        //Assert
        verify(mockViewState).addElementsToAdapter(Mockito.anyList())
    }
}