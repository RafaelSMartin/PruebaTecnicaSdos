package com.rsmartin.pruebatecnicasdos.data.model

/*
 *Array[]
 *     Objeto{}
 *          "category"  <-----
 *          "item"      <-----
 *          "farmer_id"
 *          "zipcode"
 *          "business"  <-----
 *          "l"
 *          "phone1"
 *          "website" Objeto{}
 *              "url"
 *          "location1" Objeto{}
 *              "latitude"
 *              "longitude"
 *    ----->    "human_address"
 *                  String -> "{\"address\": \"51 Main Street\", \"city\": \"Noank\", \"state\": \"CT\", \"zip\": \"06340\"}"
 *          ":@computed_region_snd5_k6zv"
 *          ":@computed_region_dam5_q64j"
 *          ":@computed_region_nhmp_cq6b"
 *          ":@computed_region_m4y2_whse"
 *
 */

data class WebServiceData(
    var category: String = "",
    var item: String = "",
    var farmer_id: String = "",
    var farm_name: String = "",
    var zipcode: String = "",
    var phone1: String = "",
    var location_1: LocationData = LocationData(),
    var business: String = "",
    var website: WebsiteData = WebsiteData()
)
