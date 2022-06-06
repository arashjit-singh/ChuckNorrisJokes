package com.chucknorrisjokes.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chucknorrisjokes.adapters.JokesListAdapter
import com.chucknorrisjokes.base.BaseActivity
import com.chucknorrisjokes.databinding.ActivityJokeListBinding
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.room.AppExecutors
import com.chucknorrisjokes.room.DocumentsRoomDatabase
import com.google.gson.Gson


class JokeListActivity : BaseActivity() {
    lateinit var binding: ActivityJokeListBinding
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(binding.recylerView.context) }
    private val mDividerItemDecoration: DividerItemDecoration by lazy {
        DividerItemDecoration(
            binding.recylerView.context,
            layoutManager.orientation
        )
    }
    private val mAdapter: JokesListAdapter by lazy {
        JokesListAdapter(listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, com.chucknorrisjokes.R.layout.activity_joke_list
        )
        setToolbarWithBackIcon(binding.toolbar, "Saved Jokes")
        getAllJokesFromDb()
    }

    private fun getAllJokesFromDb() {
        AppExecutors.getInstance().diskIO().execute {
            val db: DocumentsRoomDatabase =
                DocumentsRoomDatabase.getDatabase(binding.toolbar.context)
            val jokesList = db.jokeDao().getAllJokes()
            Log.i("Jokes", Gson().toJson(jokesList))
            runOnUiThread { setJokesAdapter(jokesList) }
        }
    }

    private fun setJokesAdapter(jokesList: List<JokeDataClass>) {
        mAdapter.updateData(jokesList)
        binding.recylerView.adapter = mAdapter
        binding.recylerView.layoutManager = layoutManager
        binding.recylerView.addItemDecoration(mDividerItemDecoration)

        if (jokesList.isEmpty())
            binding.noDataFoundImgVw.visibility = View.VISIBLE

    }

}