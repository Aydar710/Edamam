package com.m.edamam.presenters

import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.`RecommendationFragmentView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecommendationPresenterTest {

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState: `RecommendationFragmentView$$State`

    @InjectMocks
    @Spy
    private lateinit var presenter: RecommendationPresenter

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

//    @Test
//    fun whenGetRecipeShowsError() {
//        // Arrange
//        val expectedId = "1a39cf9cd8181d38ac551e5a4879ea667"
//        val expectedError = Throwable()
//        doReturn(Single.error<Recipe>(expectedError)).`when`(mockRepository).getRecipeById(expectedId)
//        presenter.getRecommendedRecipe()
//        // Assert
//        verify(mockViewState).showError(expectedError)
//    }
}