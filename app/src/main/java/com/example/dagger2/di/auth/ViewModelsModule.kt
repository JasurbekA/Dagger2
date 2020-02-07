package com.example.dagger2.di.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2.di.ViewModelKey
import com.example.dagger2.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

}
