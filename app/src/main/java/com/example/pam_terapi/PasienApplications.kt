package com.example.pam_terapi

import android.app.Application
import com.example.pam_terapi.di.AppContainer
import com.example.pam_terapi.di.PasienContainer

class PasienApplications: Application(){
        lateinit var container: AppContainer
        override fun onCreate() {
            super.onCreate()
            container= PasienContainer()
        }
    }