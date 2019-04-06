package com.m.edamam.navcomp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.m.edamam.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_first, container, false)

    override fun onStart() {
        super.onStart()
        navController = view?.let { Navigation.findNavController(it) }
        btn_next.setOnClickListener {
            navController?.navigate(R.id.action_first_to_first2)
        }
        btn_back_to_app.setOnClickListener{
            navController?.navigate(R.id.action_first_to_app)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): FirstFragment = FirstFragment()
    }
}
