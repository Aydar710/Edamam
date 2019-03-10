package com.m.edamam.presenters

import com.m.edamam.constants.RECIPE_ID_1
import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.EdamamApi
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.repositories.RecipeRepositoryDb
import com.m.edamam.views.`RecommendationFragmentView$$State`
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

    @InjectMocks
    @Spy
    private var presenter: RecommendationFragmentPresenter? = null

    @Mock
    private lateinit var mockRepository: RecipeRepository

    @Mock
    private lateinit var mockViewState: `RecommendationFragmentView$$State`

    @Before
    fun setUp() {
        //Или **@RunWith(MockitoJUnitRunner::class)**
        // MockitoAnnotations.initMocks(this)

        //presenter = Mockito.spy(RecommendationFragmentPresenter::class.java)
        presenter?.setViewState(mockViewState)
    }

    @Test
    fun getRecommendedRecipe() {
        //Arrange
        //??
        //  doReturn(Single.just(Recipe())).`when`(mockRepository.getRecipeById(RECIPE_ID_1))

        //Проверить Mockito.anyString()
        `when`(presenter?.getRandomRecipeIdFromList())
                .thenReturn(RECIPE_ID_1)

        `when`(mockRepository.getRecipeById(RECIPE_ID_1))
                .thenReturn(Single.just(Recipe()))

        //Act
        presenter?.getRecommendedRecipe()

        //Assert
        verify(mockViewState).showRecommendedRecipe(Mockito.any())
    }

    @Test
    fun getRecommendedRecipeFromDB() {
    }

    @After
    fun tearDown() {
    }
}