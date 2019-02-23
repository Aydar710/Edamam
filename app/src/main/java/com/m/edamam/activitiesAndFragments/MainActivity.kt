package com.m.edamam.activitiesAndFragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.m.edamam.R
import com.m.edamam.Retrofit
import com.m.edamam.repositories.RecipeRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)

        val retrofit = Retrofit.instance
        val repository = RecipeRepository(retrofit.getEdamamService())
    }
}
