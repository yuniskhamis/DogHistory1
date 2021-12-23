package com.yunis.doghistory1

import com.squareup.moshi.Json

data class DogImage( @Json(name = "message") val imgSrcUrl: String)
