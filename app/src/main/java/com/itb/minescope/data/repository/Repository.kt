package com.itb.minescope.data.repository

import com.itb.minescope.data.api.Api

class Repository {
    private val apiService = Api.retrofit.create(Api::class.java)
    suspend fun getOpaqueMinerals(lan: String) = apiService.getOpaqueMineral(lan)
    suspend fun getTransparentMinerals(lan: String) = apiService.getTransparentMineral(lan)
    suspend fun getOpaqueMineralSamples(lan: String) = apiService.getAllOpaqueSamples(lan)
    suspend fun getTransparentMineralSamples(lan: String) = apiService.getAllTransparentSamples(lan)
}
