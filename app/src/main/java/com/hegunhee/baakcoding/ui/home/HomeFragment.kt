package com.hegunhee.baakcoding.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hegunhee.baakcoding.R
import com.hegunhee.baakcoding.databinding.FragmentHomeBinding
import com.hegunhee.baakcoding.db.Memo
import com.hegunhee.baakcoding.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val homeAdapter = HomeAdapter(
        onMemoClick = { memo ->
            if (memo.secret) {
                Toast.makeText(requireContext(), "secretMemo", Toast.LENGTH_SHORT).show()
                showMemoDialog(memo)
            } else {
                Toast.makeText(requireContext(), "normalMemo", Toast.LENGTH_SHORT).show()
                val bundle = Bundle().apply {
                    putParcelable("Memo", memo)
                }
                findNavController().navigate(R.id.home_to_detail, bundle)
            }
        },
        onDeleteClick = { memo ->
            if (memo.secret) {
                showDeleteDialog(memo)
            } else {
                viewModel.deleteMemo(memo)
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@HomeFragment
            recyclerView.adapter = homeAdapter
        }
        initViews()
        viewModel.initList()
    }

    private fun initViews() = with(binding) {
        addMemoButton.setOnClickListener {
            findNavController().navigate(R.id.home_to_add)
        }
    }

    private fun showMemoDialog(memo: Memo) {
        val editText: EditText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("비밀번호를 입력해주세요")
            .setView(editText)
            .setPositiveButton("열기",
                DialogInterface.OnClickListener { _, _ ->
                    val text = editText.text.toString()
                    if (text == memo.secretPassWord) {
                        val bundle = Bundle().apply {
                            putParcelable("Memo", memo)
                        }
                        findNavController().navigate(R.id.home_to_detail, bundle)
                    } else {
                        Toast.makeText(requireContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            ).setNegativeButton("취소",
                DialogInterface.OnClickListener { _, _ ->

                }
            ).show()
    }

    private fun showDeleteDialog(memo: Memo) {
        val editText: EditText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("비밀번호를 입력해주세요")
            .setView(editText)
            .setPositiveButton("삭제",
                DialogInterface.OnClickListener { _, _ ->
                    val text = editText.text.toString()
                    if (text == memo.secretPassWord) {
                        viewModel.deleteMemo(memo)
                        Toast.makeText(requireContext(), "성공적으로 삭제되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            ).setNegativeButton("취소",
                DialogInterface.OnClickListener { _, _ ->

                }
            ).show()

    }

}