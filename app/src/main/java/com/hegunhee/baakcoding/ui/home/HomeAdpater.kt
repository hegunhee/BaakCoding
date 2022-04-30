package com.hegunhee.baakcoding.ui.home

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.baakcoding.databinding.MemoItemBinding
import com.hegunhee.baakcoding.db.Memo

class HomeAdpater() : RecyclerView.Adapter<HomeAdpater.HomeViewHolder>() {

    private var list: List<Memo> = listOf()

    inner class HomeViewHolder(private val binding: MemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindNormal(memo: Memo) = with(binding) {
            text.text = memo.data
            date.text = memo.time
            lock.visibility = View.GONE
        }

        fun bindSecret(memo: Memo) = with(binding) {
            text.text = memo.data
            date.text = memo.time
            lock.visibility = View.VISIBLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            MemoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        if (list[position].secret) {
            holder.bindSecret(list[position])
        } else {
            holder.bindNormal(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Memo>) {
        this.list = list
        notifyDataSetChanged()
    }


}
@BindingAdapter("bindData")
fun bindRecyclerView(recyclerView: RecyclerView?, list:List<Memo>) {
    val adapter = recyclerView?.adapter as HomeAdpater
    adapter.setData(list)
}