package com.rsmartin.pruebatecnicasdos.data.map

import android.util.Log
import com.rsmartin.pruebatecnicasdos.data.model.ItemEntity
import com.rsmartin.pruebatecnicasdos.data.model.WebServiceData

class WebServiceMap {

    companion object {
        fun map(webServiceData: WebServiceData): ItemEntity {
            var value = ItemEntity()

            value.id = 0
            value.category = webServiceData.category
            value.item = webServiceData.item
            value.farmer_id = webServiceData.farmer_id
            value.farm_name = webServiceData.farm_name
            value.zipcode = webServiceData.zipcode
            value.phone1 = webServiceData.phone1
            value.latitude = webServiceData.location_1.latitude
            value.longitude = webServiceData.location_1.longitude
            value.human_address = webServiceData.location_1.human_address
            value.url = webServiceData.website.url

            return value
        }

        fun map(item: ItemEntity): WebServiceData {
            var value = WebServiceData()

            value.category = item.category
            value.item = item.item
            value.farmer_id = item.farmer_id
            value.farm_name = item.farm_name
            value.zipcode = item.zipcode
            value.phone1 = item.phone1
            value.location_1.latitude = item.latitude
            value.location_1.longitude = item.longitude
            value.location_1.human_address = item.human_address
            value.website.url = item.url

            return value
        }

        fun map(valueItem: MutableList<ItemEntity>): ArrayList<WebServiceData> {
            var valueList = ArrayList<WebServiceData>()

            for (item in valueItem) {
                valueList.add(map(item))
            }
            Log.d("DATABASE", "MapFinal")

            return valueList
        }

        fun map(valueItem: ArrayList<WebServiceData>): MutableList<ItemEntity> {
            var valueList = ArrayList<ItemEntity>()

            for (item in valueItem) {
                valueList.add(map(item))
            }

            return valueList
        }

    }
}