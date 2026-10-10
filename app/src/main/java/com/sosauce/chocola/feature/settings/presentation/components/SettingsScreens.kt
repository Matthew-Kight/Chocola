package com.sosauce.chocola.feature.settings.presentation.components

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsScreens : NavKey {

    @Serializable
    data object Settings : SettingsScreens()

    @Serializable
    data object LookAndFeel : SettingsScreens()

    @Serializable
    data object NowPlaying : SettingsScreens()

    @Serializable
    data object Playback : SettingsScreens()

    @Serializable
    data object Library : SettingsScreens()

    @Serializable
    data object Lyrics : SettingsScreens()

    @Serializable
    data object Navigation : SettingsScreens()

    @Serializable
    data object AlwaysOnDisplay : SettingsScreens()
}