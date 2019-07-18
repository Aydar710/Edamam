package com.m.edamam

import android.support.v7.util.DiffUtil
import com.m.edamam.pojo.Hit

class HitDiffCallback : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(hitOld: Hit, hitNew: Hit): Boolean =
            hitOld == hitNew

    override fun areContentsTheSame(hitOld: Hit, hitNew: Hit): Boolean =
            hitOld.recipe?.label.equals(hitNew.recipe?.label)
}
