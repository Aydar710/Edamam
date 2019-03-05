package com.m.edamam.activitiesAndFragments

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.m.edamam.R
import kotlinx.android.synthetic.main.fragment_dialog.*

class PaginationSizeFragmentDialog : AppCompatDialogFragment() {

    var listener: PaginationSizeDialogListener? = null
    var etPaginationSize: EditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder: AlertDialog.Builder? = activity?.let { AlertDialog.Builder(it) }

        var inflater: LayoutInflater? = activity?.layoutInflater
        val view: View? = inflater?.inflate(R.layout.fragment_dialog, null)
        etPaginationSize = view?.findViewById(R.id.et_pagination_size)
        builder?.setView(view)
                ?.setTitle("Pagination Size")
                ?.setNegativeButton("Cancel") { dialog, which ->
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                ?.setPositiveButton("Ok") { dialog, which ->
                    var size: String = etPaginationSize?.text.toString()
                    listener?.setPaginationSize(Integer.parseInt(size))
                }

        return builder?.create()!!
    }

    override fun onAttach(activity: Activity?) {
        listener = activity as PaginationSizeDialogListener
        super.onAttach(activity)
    }

    interface PaginationSizeDialogListener {
        fun setPaginationSize(size: Int)
    }
}
