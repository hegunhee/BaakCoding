package com.hegunhee.baakcoding.ui.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hegunhee.baakcoding.R
import com.hegunhee.baakcoding.databinding.FragmentDetailBinding
import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel : DetailViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }
        arguments?.getParcelable<Memo>("Memo")?.run {
            binding.memoText.setText(this.data)
            viewModel.initViewModel(this)
        }
        initObserver()
        initViews()
    }

    private fun initObserver() = viewModel.detailState.observe(this){
        when(it){
            DetailState.Uninitalized ->{}
            DetailState.Detail -> {
                findNavController().popBackStack()
            }
        }
    }
    private fun initViews() = with(binding){
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}