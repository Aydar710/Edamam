package com.m.edamam.navcomp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.m.edamam.R
import kotlinx.android.synthetic.main.fragment_first2.*

class First2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_first2, container, false)

    override fun onStart() {
        super.onStart()
        btn_back2.setOnClickListener {
            view?.let { Navigation.findNavController(it) }?.navigate(R.id.action_first2_to_first)
        }
    }
}
