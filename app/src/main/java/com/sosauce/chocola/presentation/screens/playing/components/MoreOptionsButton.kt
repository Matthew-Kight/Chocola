@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.chocola.presentation.screens.playing.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sosauce.chocola.R
import com.sosauce.chocola.core.domain.player.MusicState
import com.sosauce.chocola.core.domain.player.PlayerActions
import com.sosauce.chocola.core.presentation.components.MoreOptions
import com.sosauce.chocola.core.presentation.components.TrackDropdownMenu
import com.sosauce.chocola.core.presentation.components.dialogs.DeletionDialog
import com.sosauce.chocola.core.presentation.components.dialogs.tracksDetails.TracksDetailsDialog
import com.sosauce.chocola.core.presentation.navigation.Screen
import com.sosauce.chocola.core.presentation.components.PlaylistPicker
import com.sosauce.chocola.core.presentation.util.rememberInteractionSource
import com.sosauce.nekobites.animations.AnimatedDrawable
import com.sosauce.nekobites.animations.AnimatedDrawableFile

@Composable
fun MoreOptionsButton(
    modifier: Modifier = Modifier,
    musicState: MusicState,
    onNavigate: (Screen) -> Unit,
    onShrinkToSearchbar: () -> Unit = {},
    onHandlePlayerActions: (PlayerActions) -> Unit,
) {

    val context = LocalContext.current
    var showDetailsDialog by remember { mutableStateOf(false) }
    var showMoreDialog by remember { mutableStateOf(false) }
    var showPlaylistDialog by remember { mutableStateOf(false) }
    var showDeletionDialog by remember { mutableStateOf(false) }
    val interactionSources = List(3) { rememberInteractionSource() }

    if (showDetailsDialog) {
        TracksDetailsDialog(
            track = musicState.track,
            onDismissRequest = { showDetailsDialog = false }
        )
    }

    if (showPlaylistDialog) {
        PlaylistPicker(
            mediaId = listOf(musicState.track.mediaId),
            onDismissRequest = { showPlaylistDialog = false }
        )
    }

    if (showDeletionDialog) {
        DeletionDialog(
            tracks = listOf(musicState.track),
            onDismissRequest = { showDeletionDialog = false }
        )
    }

    TrackDropdownMenu(
        track = musicState.track,
        isExpanded = showMoreDialog,
        onDismissRequest = { showMoreDialog = false },
        onNavigate = onNavigate,
        onHandlePlayerActions = onHandlePlayerActions,
        extraOptions = listOf(
            MoreOptions(
                text = { stringResource(R.string.open_eq) },
                onClick = {},
                icon = R.drawable.eq
            )
        )
    )

    ButtonGroup(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        overflowIndicator = {}
    ) {
        customItem(
            buttonGroupContent = {
                IconButton(
                    onClick = { onNavigate(Screen.Lyrics) },
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        bottomStart = 50.dp,
                        topEnd = 2.dp,
                        bottomEnd = 2.dp
                    ),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainer)
                    ),
                    interactionSource = interactionSources[0],
                    modifier = Modifier
                        .size(IconButtonDefaults.smallContainerSize(IconButtonDefaults.IconButtonWidthOption.Wide))
                        .animateWidth(interactionSources[0])
                ) {
                    Icon(
                        painter = painterResource(R.drawable.lyrics_filled),
                        contentDescription = null
                    )
                }
            },
            menuContent = {}
        )

        customItem(
            buttonGroupContent = {
                IconButton(
                    onClick = {
                        onShrinkToSearchbar()
                        onNavigate(Screen.Queue)
                    },
                    shape = RoundedCornerShape(
                        topStart = 2.dp,
                        bottomStart = 2.dp,
                        topEnd = 2.dp,
                        bottomEnd = 2.dp
                    ),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainer)
                    ),
                    interactionSource = interactionSources[1],
                    modifier = Modifier
                        .size(IconButtonDefaults.smallContainerSize(IconButtonDefaults.IconButtonWidthOption.Wide))
                        .animateWidth(interactionSources[1])
                ) {
                    Icon(
                        painter = painterResource(R.drawable.queue),
                        contentDescription = null
                    )
                }
            },
            menuContent = {}
        )
        customItem(
            buttonGroupContent = {
                IconButton(
                    onClick = {
                        showMoreDialog = !showMoreDialog
                    },
                    shape = RoundedCornerShape(
                        topStart = 2.dp,
                        bottomStart = 2.dp,
                        topEnd = 50.dp,
                        bottomEnd = 50.dp
                    ),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainer)
                    ),
                    interactionSource = interactionSources[2],
                    modifier = Modifier
                        .size(IconButtonDefaults.smallContainerSize(IconButtonDefaults.IconButtonWidthOption.Wide))
                        .animateWidth(interactionSources[2])
                ) {
                    AnimatedDrawable(
                        drawable = AnimatedDrawableFile.MORE_HOR,
                        atEnd = showMoreDialog
                    )
                }
            },
            menuContent = {}
        )
    }



}
