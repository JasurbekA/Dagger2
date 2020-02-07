package com.example.dagger2.di

import androidx.lifecycle.ViewModelProvider
import com.example.dagger2.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModuleFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory


}
