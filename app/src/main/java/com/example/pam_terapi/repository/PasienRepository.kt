package com.example.pam_terapi.repository

import com.example.pam_terapi.model.Pasien
import com.example.pam_terapi.service_api.PasienService
import okio.IOException

interface PasienRepository{
    suspend fun insertPasien(pasien: Pasien)

    suspend fun getPasien(): List<Pasien>

    suspend fun updatePasien(idPasien: String, pasien: Pasien)

    suspend fun deletePasien(idPasien: String)

    suspend fun getPasienById(idPasien: String): Pasien
}

class NetworkKontakRepository(
    private val kontakApiService: PasienService
): PasienRepository{
    override suspend fun insertPasien(pasien: Pasien) {
        kontakApiService.insertPasien(pasien)
    }

    override suspend fun updatePasien(idPasien: String, pasien: Pasien) {
        kontakApiService.updatePasien(idPasien, pasien)
    }

    override suspend fun deletePasien(idPasien: String) {
        try {
            val response = kontakApiService.deletePasien(idPasien)
            if (!response.isSuccessful){
                throw IOException("Failed to delete Kontak. HTTP Status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getPasien(): List<Pasien> =
        kontakApiService.getAllPasien()

    override suspend fun getPasienById(idPasien: String): Pasien {
        return kontakApiService.getPasienById(idPasien)
    }
}