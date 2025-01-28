package com.example.pam_terapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SesiTerapi(
    @SerialName("id_sesi")
    val idSesi: String,

    @SerialName("id_pasien")
    val idPasien: String,
    @SerialName("id_terapis")
    val idTerapis: String,
    @SerialName("id_jenis_terapi")
    val idJenisTerapi: String,

    @SerialName("tanggal_sesi")
    val tanggalSesi: String,

    @SerialName("catatan_sesi")
    val catatanSesi: String,
)

@Serializable
data class AllSesiResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pasien>
)

@Serializable
data class SesiDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pasien
)