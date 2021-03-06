package com.example.diplomapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diplomapp.R
import com.example.diplomapp.room.Flower
import com.example.diplomapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_catalog.view.*

@AndroidEntryPoint
class MainFragment:Fragment() {
    private val flowerViewModel:MainViewModel by viewModels()
    private lateinit var myAdapter :AdapterFlower

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_main,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager= LinearLayoutManager(context)
        myAdapter=AdapterFlower(emptyList())
        recycler_view.adapter =myAdapter

        flowerViewModel.getListFlowers().observeNonNull(this){
            myAdapter.setData(it)
        }
    }


    inner class AdapterFlower(private var values:List<Flower>?): RecyclerView.Adapter<AdapterFlower.FlowerViewHolder>() {
        override fun getItemCount(): Int {
            return values!!.size
        }
        inner class FlowerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(item: Flower) {
                itemView.tagsTV.text=item.tags
                Glide.with(context!!).load(item.url).into(itemView.photoItem)
                itemView.downloadedTV.text=item.downloaded
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
            return FlowerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catalog, parent, false))}


        override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
            values?.get(position)?.let { holder.bind(it) }
        }

        fun setData(newData: List<Flower>) {
            this.values = newData
            notifyDataSetChanged()
        }
    }

    private fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, onItem: (T) -> Unit) {
        this.observe(lifecycleOwner, object : NonNullObserver<T> {
            override fun onChangedNonNull(t: T) {
                onItem(t)
            }
        })
    }


}