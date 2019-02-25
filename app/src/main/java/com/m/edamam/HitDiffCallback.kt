package com.m.edamam

import android.support.v7.util.DiffUtil
import com.m.edamam.pojo.Hit

class HitDiffCallback : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(p0: Hit, p1: Hit): Boolean = p0 == p1


    override fun areContentsTheSame(p0: Hit, p1: Hit): Boolean =
            p0.recipe?.label.equals(p1.recipe?.label)

}
