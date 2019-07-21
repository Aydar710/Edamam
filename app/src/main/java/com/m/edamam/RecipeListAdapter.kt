package com.m.edamam

import android.annotation.SuppressLint
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.m.edamam.pojo.Hit
import com.m.edamam.pojo.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_recipe.*

open class RecipeListAdapter : ListAdapter<Hit, RecipeListAdapter.RecipeHolder>(HitDiffCallback()) {

    var listItemClickListener: ListItemClickListener? = null
    var hitList: ArrayList<Hit>? = null

    init {
        hitList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_recipe, parent, false)
        return RecipeHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        getItem(position).recipe?.let {
            holder.bind(it)
        }
    }

    override fun submitList(list: MutableList<Hit>?) {
        hitList?.also {
            it.clear()
            it.addAll(list as ArrayList)
        }
        super.submitList(list)
    }

    fun getList(): ArrayList<Hit> {
        val hitList: ArrayList<Hit> = ArrayList()
        for (i in 0 until itemCount) {
            hitList.add(getItem(i))
        }
        return hitList
    }

    inner class RecipeHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            txt_label.text = recipe.label
            txt_calories.text = recipe.calories?.toInt().toString() + " Kcal"

            containerView.setOnClickListener {
                recipe.let { itRecipe ->
                    listItemClickListener?.onClick(itRecipe)
                }
            }

            Picasso.get()
                    .load(recipe.image)
                    .into(img_recipe)

        }

    }

    interface ListItemClickListener {
        fun onClick(recipe: Recipe)
    }
}
