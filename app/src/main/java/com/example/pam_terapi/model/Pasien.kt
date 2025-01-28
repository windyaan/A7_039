package com.example.pam_terapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pasien(
    @SerialName("id_pasien")
    val idPasien: String,

    @SerialName("nama_pasien")
    val namaPasien: String,
    val alamat : String,

    @SerialName("nomor_telepon")
    val nomorTelepon : String,

    @SerialName("tanggal_lahir")
    val tanggalLahir : String,

    @SerialName("riwayat_medikal")
    val riwayatMedikal : String,
)

@Serializable
data class AllPasienResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pasien>
)

@Serializable
data class PasienDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pasien
)
