package com.m.edamam.presenters

import com.m.edamam.pojo.Recipe
import com.m.edamam.repositories.RecipeRepository
import com.m.edamam.views.DetailsFragmentView
import com.m.edamam.views.`DetailsFragmentView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsPresenterTest {

    @Mock
    private lateinit var repository: RecipeRepository

    @Mock
    lateinit var mockViewState: `DetailsFragmentView$$State`

    @InjectMocks
    @Spy
    lateinit var presenter: DetailsPresenter

    @Before
    fun setUp() {
        presenter.setViewState(mockViewState)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(DetailsFragmentView::class.java)
        presenter.attachView(mockView)
        verify(mockViewState).loadRecipeDetails()
    }

    @Test
    fun whenGetRecipeDetailsExpectedSuccess() {
        // Arrange
        val expectedId = "1a39cf9cd8181d38ac551e5a4879ea66"
        val mockRecipe = mock(Recipe::class.java)
        doReturn(Single.just(mockRecipe)).`when`(repository).getRecipeById(expectedId)
        // Act
        presenter.getRecipeDetails(expectedId)
        // Assert
        verify(mockViewState).showRecipeDetails(mockRecipe)
    }

    @Test
    fun whenGetRecipeDetailsExpectedError() {
        // Arrange
        val expectedId = "1a39cf9cd8181d38ac551e5a4879ea667"
        val expectedError = Throwable()
        doReturn(Single.error<Recipe>(expectedError)).`when`(repository).getRecipeById(expectedId)
        // Act
        presenter.getRecipeDetails(expectedId)
        // Assert
        verify(mockViewState).handleError(expectedError)
    }
}
