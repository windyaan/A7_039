package com.example.pam_terapi.service_api

import com.example.pam_terapi.model.AllPasienResponse
import com.example.pam_terapi.model.Pasien
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PasienService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertPasien(@Body pasien: Pasien)

    @GET("pasien")
    suspend fun getAllPasien(): AllPasienResponse

    @GET("id_pasien/{id_pasien}")
    suspend fun getPasienById(@Path("id_pasien") idPasien:String): Pasien

    @PUT("id_pasien/{id_pasien}")
    suspend fun updatePasien(@Path("id_pasien") idPasien:String, @Body pasien: Pasien)

    @DELETE("id_pasien/{id_pasien}")
    suspend fun deletePasien(@Path("id_pasien")idPasien: String): Response<Void>

//    @POST("insertpasien.php")
//    suspend fun insertPasien(@Body pasien: Pasien)
//
//    @GET("bacapasien.php")
//    suspend fun getAllPasien(): List<Pasien>
//
//    @GET("baca1pasien.php/{id_pasien}")
//    suspend fun getPasienById(@Query("id_pasien") idPasien:String): Pasien
//
//    @PUT("editpasien.php/{id_pasien}")
//    suspend fun updatePasien(@Query("id_pasien") idPasien:String, @Body pasien: Pasien)
//
//    @DELETE("deletepasien.php/{id_pasien}")
//    suspend fun deletePasien(@Query("id_pasien")idPasien: String): Response<Void>
}