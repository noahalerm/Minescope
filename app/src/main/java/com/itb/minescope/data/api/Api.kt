package com.itb.minescope.data.api

import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.OpaqueMineralSample
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.data.models.TransparentMineralSample
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {
    companion object {
        //API CALL
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://minescope-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    //MINERALS

    //TRANSPARENT
    //EN
    @GET("/en/transparentminerals")
    fun getTransparentMineralEN(): Call<List<TransparentMineral>>

    //ES
    @GET("/sp/transparentminerals")
    fun getTransparentMineralES(): Call<List<TransparentMineral>>

    //EN
    @GET("/ca/transparentminerals")
    fun getTransparentMineralCAT(): Call<List<TransparentMineral>>

    //OPAQUE
    @GET("/en/opaqueminerals")
    fun getOpaqueMineralEN(): Call<List<OpaqueMineral>>

    //ES
    @GET("/sp/opaqueminerals")
    fun getOpaqueMineralES(): Call<List<OpaqueMineral>>

    //EN
    @GET("/ca/opaqueminerals")
    fun getOpaqueMineralCAT(): Call<List<OpaqueMineral>>

    //SAMPLES

    //TRANSPARENT
    //EN
    @GET("/en/transparentminerals/{idMineral}/transparentsamples")
    fun getTransparentSampleEN(@Path("idMineral") idMineral: Int): Call<List<TransparentMineralSample>>

    //ES
    @GET("/sp/transparentminerals/{idMineral}/transparentsamples")
    fun getTransparentSampleES(@Path("idMineral") idMineral: Int): Call<List<TransparentMineralSample>>

    //EN
    @GET("/ca/transparentminerals/{idMineral}/transparentsamples")
    fun getTransparentSampleCAT(@Path("idMineral") idMineral: Int): Call<List<TransparentMineralSample>>

    //OPAQUE
    @GET("/en/opaqueminerals/{idMineral}/opaquesamples")
    fun getOpaqueSampleEN(@Path("idMineral") idMineral: Int): Call<List<OpaqueMineralSample>>

    //ES
    @GET("/sp/opaqueminerals/{idMineral}/opaquesamples")
    fun getOpaqueSampleES(@Path("idMineral") idMineral: Int): Call<List<OpaqueMineralSample>>

    //EN
    @GET("/ca/opaqueminerals/{idMineral}/opaquesamples")
    fun getOpaqueSampleCAT(@Path("idMineral") idMineral: Int): Call<List<OpaqueMineralSample>>
}
