package com.chucknorrisjokes.ui.dialogue

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.chucknorrisjokes.R
import com.chucknorrisjokes.databinding.JokeRowItemBinding
import com.chucknorrisjokes.utils.extensions.toPx
import com.chucknorrisjokes.utils.extensions.updateHeight


class ShowJokeDialogue : DialogFragment() {

    companion object {
        val TAG = "ShowJokeDialogue"
    }

    lateinit var binding: JokeRowItemBinding

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
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.joke_row_item, container, false)

        if (arguments != null) {
            binding.jokeTxtVw.text = arguments!!.getString("joke")
        }

        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.okBtn.setOnClickListener {
            dismiss()

        }
    }


}