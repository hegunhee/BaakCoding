package com.hegunhee.baakcoding.ui.home

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.baakcoding.databinding.MemoItemBinding
import com.hegunhee.baakcoding.db.Memo

class HomeAdapter(
    val onMemoClick : (Memo) -> Unit,
    val onDeleteClick : (Memo) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var list: List<Memo> = listOf()

    inner class HomeViewHolder(private val binding: MemoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(memo : Memo) = with(binding){
            text.text = memo.data
            date.text = memo.time
            lock.visibility = if(memo.secret){
                View.VISIBLE
            }else{
                View.GONE
            }
            root.setOnClickListener {
                onMemoClick(memo)
            }
            delete.setOnClickListener {
                onDeleteClick(memo)
            }
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
        holder.bind(list[position])
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
    val adapter = recyclerView?.adapter as HomeAdapter
    adapter.setData(list)
}