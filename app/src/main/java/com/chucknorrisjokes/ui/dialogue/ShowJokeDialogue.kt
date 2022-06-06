package com.chucknorrisjokes.ui.dialogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.chucknorrisjokes.R
import com.chucknorrisjokes.databinding.JokeRowItemBinding
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.room.AppExecutors
import com.chucknorrisjokes.room.DocumentsRoomDatabase
import com.chucknorrisjokes.utils.UtilityClass


class ShowJokeDialogue : DialogFragment() {

    companion object {
        const val TAG = "ShowJokeDialogue"
    }

    lateinit var binding: JokeRowItemBinding
    private lateinit var joke: JokeDataClass

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
        binding = DataBindingUtil.inflate(inflater, R.layout.joke_row_item, container, false)

        if (arguments != null) {
            joke = arguments!!.getSerializable("joke") as JokeDataClass
            binding.jokeTxtVw.text = joke.value
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.okBtn.setOnClickListener {
            dismiss()

        }

        binding.saveBtn.setOnClickListener {

            AppExecutors.getInstance().diskIO().execute {
                val db: DocumentsRoomDatabase =
                    DocumentsRoomDatabase.getDatabase(binding.jokeTxtVw.context)
                db.jokeDao().insertJoke(joke)
                activity?.runOnUiThread {
                    UtilityClass.showToast(
                        binding.root.context,
                        getString(R.string.confirm_saved_local)
                    )
                    dismiss()
                }
            }
        }
    }


}