package com.chucknorrisjokes.callbacks

import android.view.View

open interface OnClickHandlerInterface {
    fun onRandomJokeClick(view: View)
    fun onSearchJokeClick(view: View)
    fun onJokeCategoryClick(view: View)
}
