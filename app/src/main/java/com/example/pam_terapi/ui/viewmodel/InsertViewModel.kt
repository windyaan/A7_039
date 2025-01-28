package com.example.pam_terapi.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_terapi.model.Pasien
import com.example.pam_terapi.repository.PasienRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val ps: PasienRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPsState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent=insertUiEvent)
    }

    suspend fun insertPs(){
        viewModelScope.launch {
            try {
                ps.insertPasien(uiState.insertUiEvent.toPs())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idPasien: String = "",
    val namaPasien: String = "",
    val alamat: String = "",
    val nomorTelepon: String = "",
    val tanggalLahir: String = "",
    val riwayatMedikal: String = "",
)

fun InsertUiEvent.toPs(): Pasien = Pasien(
    idPasien = idPasien,
    namaPasien = namaPasien,
    alamat = alamat,
    nomorTelepon = nomorTelepon,
    tanggalLahir = tanggalLahir,
    riwayatMedikal = riwayatMedikal
)

fun Pasien.toUiStatePs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pasien.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idPasien = idPasien,
    namaPasien = namaPasien,
    alamat = alamat,
    nomorTelepon = nomorTelepon,
    tanggalLahir = tanggalLahir,
    riwayatMedikal = riwayatMedikal
)