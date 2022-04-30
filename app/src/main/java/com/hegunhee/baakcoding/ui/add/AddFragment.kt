package com.hegunhee.baakcoding.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hegunhee.baakcoding.R
import com.hegunhee.baakcoding.databinding.FragmentAddBinding
import com.hegunhee.baakcoding.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(R.layout.fragment_add) {

    private val viewModel : AddViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner= this@AddFragment
        }
    }
}