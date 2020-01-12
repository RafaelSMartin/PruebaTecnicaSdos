package com.rsmartin.pruebatecnicasdos.presentation.internal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.data.model.HumanAddress
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData
import kotlinx.android.synthetic.main.item_layout.view.*

class WebServiceAdapter(val itemList: List<WebServiceData>) :
    RecyclerView.Adapter<WebServiceAdapter.WebServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebServiceViewHolder {
        var layoutInflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return WebServiceViewHolder(layoutInflate)
    }

    override fun onBindViewHolder(holder: WebServiceViewHolder, position: Int) {
        var item = itemList[position]
        holder.bindPartido(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class WebServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPartido(item: WebServiceData) {

            if(item.farm_name == ""){
                itemView.tvFarmName.text = item.category
            } else {
                itemView.tvFarmName.text = item.farm_name
            }

            itemView.tvCategory.text = item.item
            try {
                val address =
                    Gson().fromJson(item.location_1.human_address, HumanAddress::class.java)
                itemView.tvLocationDir.text =
                    address.address + ", " + address.city + ", " + address.state + ", " + address.zip
            } catch (exception: JsonSyntaxException) {
            }

            if (item.website != null) {
                itemView.tvWebsite.text = item.website.url
            }
        }
    }
}