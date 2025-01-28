package com.example.pam_terapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Terapis(
    @SerialName("id_terapis")
    val idTerapis: String,

    @SerialName("nama_terapis")
    val namaTerapis: String,

    @SerialName("spesialisasi")
    val spesialisasi : String,

    @SerialName("nomor_izin_praktik")
    val nomorIzinPraktik : String,
)
