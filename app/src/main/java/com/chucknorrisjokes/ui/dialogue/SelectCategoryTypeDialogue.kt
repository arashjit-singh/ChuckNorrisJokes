package com.chucknorrisjokes.ui.dialogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chucknorrisjokes.R
import com.chucknorrisjokes.adapters.CategoryListAdapter
import com.chucknorrisjokes.constants.Constants
import com.chucknorrisjokes.databinding.LayoutRecyclerViewBinding
import com.chucknorrisjokes.utils.UtilityClass

class SelectCategoryTypeDialogue : DialogFragment() {

    companion object {
        const val TAG = "SelectCategoryTypeDialogue"
    }

    lateinit var binding: LayoutRecyclerViewBinding
    private val mContext by lazy { context }
    private var mVehicleList: (ArrayList<String>)? = null
    private var mAdapter: CategoryListAdapter? = null
    var callback: CategoryListAdapter.ItemClick? = null

    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(binding.categoryRecyclerView.context) }
    private val mDividerItemDecoration: DividerItemDecoration by lazy {
        DividerItemDecoration(
            binding.categoryRecyclerView.context,
            layoutManager.orientation
        )
    }

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
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_recycler_view, container, false)
        if (arguments != null) {
            mVehicleList =
                arguments!!.getSerializable(Constants.BUNDLE_CONSTANT_LIST) as ArrayList<String>?
            binding.headerTxtVw.text = arguments!!.getSerializable(Constants.BUNDLE_CONSTANT_HEADER) as String
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        binding.cancelButton.setOnClickListener { dismiss() }
    }

    private fun setAdapter() {
        val recyclerView = binding.categoryRecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(mDividerItemDecoration)
        mAdapter = mVehicleList?.let { CategoryListAdapter(it) }
        if (callback != null)
            mAdapter?.callback = callback
        recyclerView.adapter = mAdapter
        mContext.let { UtilityClass.setRecyclerViewAnimation(recyclerView, it!!) }
    }

}