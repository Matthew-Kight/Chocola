package com.sosauce.chocola.core.domain.library

import androidx.compose.ui.util.fastFilter
import com.sosauce.chocola.core.domain.util.regex
import com.sosauce.chocola.core.domain.util.thenIf
import com.sosauce.chocola.data.datastore.SearchSettings
import com.sosauce.chocola.data.models.Album
import com.sosauce.chocola.data.models.Artist
import com.sosauce.chocola.data.models.CuteTrack
import com.sosauce.chocola.utils.AlbumSort
import com.sosauce.chocola.utils.ArtistSort

fun List<CuteTrack>.search(
    query: String,
    searchSettings: SearchSettings,
): List<CuteTrack> {
    val regexPattern = query.regex(searchSettings.matchCase)
    return fastFilter { track ->
        if (searchSettings.regex) {
            regexPattern.containsMatchIn(track.title)
        } else {
            track.title.contains(query, !searchSettings.matchCase)
        }
    }
}


fun List<Album>.ordered(
    sort: AlbumSort,
    regex: Boolean,
    matchCase: Boolean,
    ascending: Boolean,
    query: String
): List<Album> {
    val regexPattern = query.regex(matchCase)

    val filtered = this.fastFilter { track ->
        if (regex) {
            regexPattern.containsMatchIn(track.name)
        } else {
            track.name.contains(query, !matchCase)
        }
    }

    return filtered
        .sortedBy {
            when (sort) {
                AlbumSort.NAME -> it.name
                AlbumSort.ARTIST -> it.artist
            }
        }.thenIf(!ascending) { asReversed() }
}

fun List<Artist>.ordered(
    sort: ArtistSort,
    regex: Boolean,
    matchCase: Boolean,
    ascending: Boolean,
    query: String
): List<Artist> {
    val regexPattern = query.regex(matchCase)

    val filtered = this.fastFilter { track ->
        if (regex) {
            regexPattern.containsMatchIn(track.name)
        } else {
            track.name.contains(query, !matchCase)
        }
    }

    return filtered
        .sortedBy {
            when (sort) {
                ArtistSort.NAME -> it.name
                ArtistSort.NB_ALBUMS -> it.numberAlbums.toString()
                ArtistSort.NB_TRACKS -> it.tracks.size.toString()
            }
        }.thenIf(!ascending) { asReversed() }
}

fun List<CuteTrack>.orderAlbumTrackNumber(): List<CuteTrack> {
    return sortedWith(
        compareBy(
            { it.trackNumber == 0 },
            { it.trackNumber }
        )
    )
}
