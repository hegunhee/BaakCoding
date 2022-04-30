package com.hegunhee.baakcoding.ui.home

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hegunhee.baakcoding.R
import com.hegunhee.baakcoding.databinding.FragmentHomeBinding
import com.hegunhee.baakcoding.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel= viewModel
            lifecycleOwner = this@HomeFragment
        }
        initViews()
    }

    private fun initViews() = with(binding){
        addMemoButton.setOnClickListener {
            findNavController().navigate(R.id.home_to_add)
        }
    }
}