package com.m.edamam

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_recipe.view.*

class RecipeListAdapter : ListAdapter<Hit, RecipeListAdapter.RecipeHolder>(HitDiffCallback()) {

    var listItemClickListener: ListItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_recipe, parent, false)
        return RecipeHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        getItem(position).recipe?.let {
            holder.bind(it)
        }
    }

    inner class RecipeHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        var imgRecipe = containerView.img_recipe
        var txtLabel = containerView.txt_label
        var txtCalories = containerView.txt_calories
        var txtTime = containerView.txt_time

        fun bind(recipe: Recipe) {
            txtLabel.text = recipe.label
            txtCalories.text = recipe.calories.toString()
            txtTime.text = recipe.totalTime.toString()

            containerView.setOnClickListener {
                recipe.let { itRecipe ->
                    listItemClickListener?.onClick(itRecipe)
                }
            }

            Picasso.get()
                    .load(recipe.image)
                    .into(imgRecipe)

        }

    }

    fun getList(): ArrayList<Hit> {
        var hitList : ArrayList<Hit> = ArrayList()
        for (i in 0 until itemCount){
            hitList.add(getItem(i))
        }
        return hitList
    }

    interface ListItemClickListener {
        fun onClick(recipe: Recipe)
    }
}