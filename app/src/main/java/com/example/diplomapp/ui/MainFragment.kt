package com.example.diplomapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diplomapp.R
import com.example.diplomapp.room.Flower
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_catalog.view.*

@AndroidEntryPoint
class MainFragment:Fragment() {
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
        val items: ArrayList<Flower>? = arrayListOf()



        myAdapter=AdapterFlower(items)
        recycler_view.adapter =myAdapter

    }


    inner class AdapterFlower(private val values:ArrayList<Flower>?): RecyclerView.Adapter<AdapterFlower.FlowerViewHolder>() {
        override fun getItemCount(): Int {
            return values!!.size
        }
        inner class FlowerViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(item: Flower) {
                itemView.priceTV.text=item.price
                Glide.with(context!!).load(item.url).into(itemView.photoItem)
                itemView.descriptionTV.text=item.name
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
            return FlowerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catalog, parent, false))}


        override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
            values?.get(position)?.let { holder.bind(it) }
        }

        fun addList(items:List<Flower>) {
            values?.addAll(items)
        }


    }




}