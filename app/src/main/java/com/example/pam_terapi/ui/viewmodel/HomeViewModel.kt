package com.example.pam_terapi.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pam_terapi.model.Pasien
import com.example.pam_terapi.repository.PasienRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val pasien: List<Pasien>):HomeUiState()
    object Error:HomeUiState()
    object Loading:HomeUiState()
}

class HomeViewModel(private val ps: PasienRepository): ViewModel() {
    var psUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPs()
    }

    fun getPs() {
        viewModelScope.launch {
            psUiState = HomeUiState.Loading
            psUiState = try {
                HomeUiState.Success(ps.getPasien())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deletePs(idPasien: String){
        viewModelScope.launch {
            try {
                ps.deletePasien(idPasien)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}