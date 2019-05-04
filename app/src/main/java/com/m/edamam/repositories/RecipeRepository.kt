package com.m.edamam.repositories

import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe

open class RecipeRepository(private val api: EdamamApi, private var repositoryDb: RecipeRepositoryDb) {

    suspend fun getRecipesByName(query: String): List<Hit> {
        val result = api.getRecipesByNameAsync(query).await()
        return result.hits ?: ArrayList(1)
    }

    open suspend fun getRecipeById(query: String): Recipe? {
        val result = api.getRecipeByIdAsync(query).await()
        val recipe = result.hits?.get(0)?.recipe
        repositoryDb.clear()
        recipe?.let { repositoryDb.save(it) }
        return recipe
    }

    suspend fun getRecipesByName(query: String, currentPage: Int, pagSize: Int): List<Hit> {
        val result = api.getRecipesByNameAsync(query,
                from = "${pagSize * currentPage}", to = "${pagSize * (currentPage + 1)}").await()
        return result.hits ?: ArrayList(1)
    }
}
