package com.minwook.faceaiproject.network

import com.minwook.faceaiproject.network.response.CelebrityResponse
import com.minwook.faceaiproject.network.response.FaceResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

class FaceRouter : RetrofitCreator() {

    companion object {
        fun api(): FaceAPI {
            return create(FaceAPI::class.java)
        }
    }

    interface FaceAPI {
        @Multipart
        @POST("v1/vision/celebrity")
        fun postCelebrityFace(@Field("name") name: String = "image", @Field("filename") filename: String): Single<CelebrityResponse>

        @Multipart
        @POST("v1/vision/face")
        fun postFaceInfo(@Field("name") name: String = "image", @Field("filename") filename: String): Single<FaceResponse>
    }
}