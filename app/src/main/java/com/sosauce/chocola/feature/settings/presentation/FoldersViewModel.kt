package com.sosauce.chocola.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sosauce.chocola.core.data.library.FoldersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FoldersViewModel(
    private val foldersRepository: FoldersRepository
) : ViewModel() {

    val folders = foldersRepository.fetchLatestMusicFolders().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

}