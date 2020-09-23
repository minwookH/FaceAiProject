package com.minwook.faceaiproject.network.response

data class CelebrityResponse(
    val info: CommonInfo,
    val faces: ArrayList<Celebrity>
)

data class CommonInfo(
    val size: CommonInfoSize,
    val faceCount: Number
)

data class CommonInfoSize(
    val width: Number,
    val height: Number
)

data class Celebrity(
    val celebrity: CommonValue
)

data class CommonValue(
    val value: String,
    val confidence: Number
)