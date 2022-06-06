package com.chucknorrisjokes.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chucknorrisjokes.R
import com.chucknorrisjokes.adapters.CategoryListAdapter
import com.chucknorrisjokes.base.BaseActivity
import com.chucknorrisjokes.callbacks.OnClickHandlerInterface
import com.chucknorrisjokes.callbacks.OnSearchJokeInterface
import com.chucknorrisjokes.databinding.ActivityMainBinding
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.ui.dialogue.SearchJokeDialogue
import com.chucknorrisjokes.ui.dialogue.SelectCategoryTypeDialogue
import com.chucknorrisjokes.ui.dialogue.ShowJokeDialogue
import com.chucknorrisjokes.utils.UtilityClass
import com.chucknorrisjokes.viewmodel.MainActivityViewModel
import java.io.Serializable

class MainActivity : BaseActivity(), OnClickHandlerInterface, CategoryListAdapter.ItemClick,
    OnSearchJokeInterface {

    lateinit var binding: ActivityMainBinding
    private val mContext by lazy { this@MainActivity }

    private val mViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
        binding.model = mViewModel
        binding.clickHandler = this

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.ic_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_saved_jokes -> {
            // User chose the "downloads" item, show the app settings UI...
            // Start your app main activity
            startActivity(
                Intent(this, JokeListActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle(),
            )
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


    override fun onRandomJokeClick(view: View) {
        if (checkConnectivityAndShowDialogue()) {
            mViewModel.getJoke().observe(this, { joke ->
                showJokeDialogue(joke)
            })
        }
    }

    override fun onSearchJokeClick(view: View) {
        val dialog = SearchJokeDialogue()
        dialog.callback = this
        val ft = mContext.supportFragmentManager.beginTransaction()
        val args = Bundle()
        dialog.arguments = args
        dialog.isCancelable = true
        dialog.show(ft, SelectCategoryTypeDialogue.TAG)
    }

    override fun onJokeCategoryClick(view: View) {
        if (checkConnectivityAndShowDialogue()) {
            mViewModel.getListOfCategories().observe(this, { categories ->
                showList(categories, "List of Categories", this)
            })
        }
    }

    override fun onItemClick(category: String) {
        if (checkConnectivityAndShowDialogue()) {
            mViewModel.getJokeFromCategory(category).observe(this, { joke ->
                showJokeDialogue(joke)
            })
        }
    }

    override fun onSearch(string: String) {
        if (checkConnectivityAndShowDialogue()) {
            mViewModel.searchForJoke(string.trim()).observe(this, { categories ->
                val jokeList: ArrayList<String> = arrayListOf()
                if (jokeList.isNotEmpty()) {
                    for (item in categories.result) {
                        jokeList.add(item.value)
                    }
                    showList(jokeList, "Jokes - $string", null)
                } else {
                    UtilityClass.showSnackBar(
                        binding.root,
                        getString(R.string.no_joke_found)
                    )
                }
            })
        }
    }

    private fun showJokeDialogue(joke: JokeDataClass) {
        val dialog = ShowJokeDialogue()
        val ft = mContext.supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putSerializable("joke", joke)
        dialog.arguments = args
        dialog.isCancelable = true
        dialog.show(ft, ShowJokeDialogue.TAG)
    }

    private fun showList(
        mList: ArrayList<String>,
        header: String,
        callback: CategoryListAdapter.ItemClick?
    ) {
        val dialog = SelectCategoryTypeDialogue()
        dialog.callback = callback
        val ft = mContext.supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putSerializable("categoryList", mList as Serializable)
        args.putString("header", header)
        dialog.arguments = args
        dialog.isCancelable = true
        dialog.show(ft, SelectCategoryTypeDialogue.TAG)
    }
}