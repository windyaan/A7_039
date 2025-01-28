package com.example.pam_terapi.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_terapi.model.Pasien
import com.example.pam_terapi.repository.PasienRepository
import com.example.pam_terapi.ui.view.mahasiswa.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pasienRepository: PasienRepository
) : ViewModel() {
    private val idPasien: String = checkNotNull(savedStateHandle[DestinasiDetail.ID])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getPasienById()
    }

    private fun getPasienById() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = pasienRepository.getPasienById(idPasien)
                detailUiState = DetailUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deletePs() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                pasienRepository.deletePasien(idPasien)

                detailUiState = DetailUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}


data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

fun Pasien.toDetailUiEvent(): InsertUiEvent{
    return InsertUiEvent(
        idPasien = idPasien,
        namaPasien = namaPasien,
        alamat = alamat,
        nomorTelepon = nomorTelepon,
        tanggalLahir = tanggalLahir,
        riwayatMedikal = riwayatMedikal
    )
}