package com.peal.spacestationapp.data.remote

import com.peal.spacestationapp.data.remote.model.IssResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Peal Mazumder on 30/1/25.
 */


interface IssApiService {
    @GET("iss-now.json")
    suspend fun getIssLocation(): Response<IssResponse>
}