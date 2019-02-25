package com.m.edamam.utils

import android.support.v7.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun observableFromSearchView(searchView: SearchView): Observable<String> {
    val subject: PublishSubject<String> = PublishSubject.create()
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            subject.onComplete()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            subject.onNext(newText ?: "")
            return true
        }
    })
    return subject
}
