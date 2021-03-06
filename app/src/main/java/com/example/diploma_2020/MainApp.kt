package com.example.diploma_2020

import android.app.Application
import com.example.diploma_2020.data.DiplomaService
import com.example.diploma_2020.data.DiplomaServiceBuilder
import com.example.diploma_2020.data.Repository
import com.example.diploma_2020.ui.monuments.MonumentsViewModel
import com.example.diploma_2020.ui.home.HomeViewModel
import com.example.diploma_2020.ui.study_bars.StudyBarsViewModel
import com.example.diploma_2020.ui.traditional.TraditionalViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainApp : Application() {
    private fun homeModule(service: DiplomaService) = module {
        single { Repository(androidContext(), service) }
        single { HomeViewModel(get()) }
        viewModel { TraditionalViewModel(get()) }
        viewModel { StudyBarsViewModel(get()) }
    }

    private fun galleryModule(service: DiplomaService) = module {
        single { MonumentsViewModel(get()) }
    }

    private val loginModule = module {
        viewModel { LoginViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        val service = DiplomaServiceBuilder.buildService(DiplomaService::class.java)

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(homeModule(service), galleryModule(service), loginModule))
        }
    }
}