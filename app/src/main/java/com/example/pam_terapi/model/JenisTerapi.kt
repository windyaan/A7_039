package com.example.pam_terapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JenisTerapi(
    @SerialName("id_jenis_terapi")
    val idJenisTerapi: String,

    @SerialName("nama_jenis_terapi")
    val namaJenisTerapi: String,

    @SerialName("deskripsi_terapi")
    val deskripsiTerapi : String,
)

@Serializable
data class AllJenisResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pasien>
)

@Serializable
data class JenisDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pasien
)