package com.example.pam_terapi.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_terapi.PasienApplications

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiKontak().container.kontakRepository) }
        initializer { InsertViewModel(aplikasiKontak().container.kontakRepository) }
        initializer { DetailViewModel(createSavedStateHandle(),aplikasiKontak().container.kontakRepository)}
        initializer { UpdateViewModel(createSavedStateHandle(), aplikasiKontak().container.kontakRepository) }
    }
}

fun CreationExtras.aplikasiKontak(): PasienApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PasienApplications)