package com.example.composecourseyt.data.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecourseyt.data.remote.NigerianBanksApi
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankViewModel : ViewModel() {
    // Define the base URL for the Nigerian Banks API
    private val baseUrl = "https://nigerianbanks.xyz" // Replace with your actual base URL
//      private val baseUrl= "http://192.168.100.29:9090"
    // Create a Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the NigerianBanksApi interface
    private val apiService = retrofit.create(NigerianBanksApi::class.java)

    val nigerianBanks = mutableStateOf<List<NigerianBanksApi.BankInfo>>(emptyList())

    fun fetchBanks() {
        viewModelScope.launch {
            try {
                val response: Response<List<NigerianBanksApi.BankInfo>> = apiService.getNigerianBanks()
                if (response.isSuccessful) {
                    val banks = response.body() ?: emptyList()
                    nigerianBanks.value = banks
                    Log.d("BankViewModel", "Data loaded successfully: $banks") // Log data retrieval
                } else {
                    Log.e("BankViewModel", "API request was not successful. Status code: ${response.code()}, Message: ${response.message()}")
                    // Handle the error
                }
            } catch (e: Exception) {
                Log.e("BankViewModel", "Exception while fetching data: ${e.message}")
                // Handle the error
            }
        }
    }
}
