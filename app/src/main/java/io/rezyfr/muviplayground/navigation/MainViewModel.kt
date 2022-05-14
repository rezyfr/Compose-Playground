package io.rezyfr.muviplayground.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rezyfr.provider.ThemeProvider
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val themeProvider: ThemeProvider) : ViewModel() {
    fun themeProvider() = themeProvider
}