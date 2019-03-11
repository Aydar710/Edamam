package com.m.edamam.presenters

import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.`DetailsFragmentView$$State`
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsFragmentPresenterTest {

    @InjectMocks
    @Spy
    private var presenter : DetailsFragmentPresenter? = null

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState : `DetailsFragmentView$$State`

    @Before
    fun setUp() {
        presenter?.setViewState(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        //Arrange
        doNothing().`when`(mockViewState.loadRecipeDetails())
        //Act
//        presenter.onFirstViewAttach()
        //Verify
        verify(mockViewState).loadRecipeDetails()
    }

    @Test
    fun getRecipeDetails() {
        //Arrange
        `when`(mockRepository.getRecipeById(Mockito.anyString()))
                .thenReturn(Single.just(Recipe()))
        //Act
        presenter?.getRecipeDetails(Mockito.anyString())
        //Verify
        verify(mockViewState).showRecipeDetails(Mockito.any())
    }
}