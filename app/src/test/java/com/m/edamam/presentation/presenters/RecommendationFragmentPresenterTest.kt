package com.m.edamam.presentation.presenters

import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.data.pojo.Recipe
import com.m.edamam.data.repositories.RecipeRepository
import com.m.edamam.presentation.recommendation.RecommendationFragmentPresenter
import com.m.edamam.presentation.views.`RecommendationFragmentView$$State`
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*

import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecommendationFragmentPresenterTest {

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState: `RecommendationFragmentView$$State`

    @InjectMocks
    @Spy
    private lateinit var presenter: RecommendationFragmentPresenter

    @Before
    fun setUp() {
        presenter.setViewState(mockViewState)
    }

    @Test
    fun getRecommendedRecipe() {
        //Arrange
        `when`(mockRepository.getRecipeById(RECIPE_ID_1))
                .thenReturn(Single.just(Recipe()))
        `when`(presenter.getRandomRecipeIdFromList())
                .thenReturn(RECIPE_ID_1)
        
        //Act
        presenter.getRecommendedRecipe()

        //Assert
        verify(mockViewState).showRecommendedRecipe(Mockito.any())
    }

    @After
    fun tearDown() {
    }
}