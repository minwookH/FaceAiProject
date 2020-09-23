package com.minwook.faceaiproject.network.response

data class FaceResponse(
    val info: CommonInfo,
    val faces: ArrayList<Face>
)

data class Face(
    val roi: FaceRoi,
    val landmark: Landmark,
    val gender: CommonValue,
    val age: CommonValue,
    val emotion: CommonValue,
    val pose: CommonValue
)

data class FaceRoi(
    val x: Number,
    val y: Number,
    val width: Number,
    val height: Number
)

data class Landmark(
    val leftEye: Coordinate,
    val rightEye: Coordinate,
    val nose: Coordinate,
    val leftMouth: Coordinate,
    val rightMouth: Coordinate
)

data class Coordinate(
    val x: Number,
    val y: Number
)