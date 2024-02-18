package com.cvs.worldquiz.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 *   {
"countryId": 1,
"code": " AD",
"label": "Andorra",
"capital": "Andorra la Vella",
"population": "85,458",
"area": "467.63",
"type": "PCLI",
"geoNameId": 3041565,
"continent": "Europe"
},
 * */
open class Country : RealmObject() {

    @PrimaryKey
    var countryId: Int = 0

    var code: String = ""
    var label: String? = null
    var capital: String? = null
    var population: String? = null
    var area: String? = null
    var type: String? = null
    var geoNameId: Int? = null
    var continent: String? = null

}