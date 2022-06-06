package com.chucknorrisjokes.ui.dialogue

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.chucknorrisjokes.R
import com.chucknorrisjokes.callbacks.OnSearchJokeInterface
import com.chucknorrisjokes.databinding.LayoutSearchJokeBinding
import com.chucknorrisjokes.utils.UtilityClass

class SearchJokeDialogue : DialogFragment() {

    lateinit var binding: LayoutSearchJokeBinding
    lateinit var callback: OnSearchJokeInterface

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.FullScreenDialogStyle)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_search_joke, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.okBtn.setOnClickListener {

            if (TextUtils.isEmpty(binding.edtSearchJoke.text.toString().trim())) {
                UtilityClass.hideKeyboard(binding.root)
                UtilityClass.showSnackBar(binding.root, "Uh oh!! Enter something to search")
            } else {
                dismiss()
                callback.onSearch(binding.edtSearchJoke.text.toString().trim())
            }
        }

    }

}