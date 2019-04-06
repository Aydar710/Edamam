package com.m.edamam.navcomp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.m.edamam.R
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {

    private var firstFragment: FirstFragment? = null
    private var secondFragment: SecondFragment? = null
    private var thirdFragment: ThirdFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        var navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()
        bottom_navigation.setupWithNavController(navController)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
    }
}
