package com.example.composecourseyt.data.remote


import retrofit2.Response
import retrofit2.http.GET

interface NigerianBanksApi {

    // Define a function to get a list of Nigerian banks with their codes and logos
    @GET("banks")
    suspend fun getNigerianBanks(): Response<List<BankInfo>>

    // Define a data class to represent the structure of the response
    data class BankInfo(
        val bankName: String,
        val bankCode: String,
        val bankSlug: String,
        val bankLogoUrl: String
    )
}