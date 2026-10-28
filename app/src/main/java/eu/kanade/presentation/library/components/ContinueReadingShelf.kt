package eu.kanade.presentation.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.kanade.tachiyomi.ui.library.LibraryItem
import tachiyomi.domain.library.model.LibraryManga
import tachiyomi.domain.manga.model.MangaCover
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource

/**
 * Items for the "Continue reading" shelf: manga with unread chapters,
 * most recently read first, capped at 10.
 */
internal fun List<LibraryItem>.continueReadingShelfItems(): List<LibraryItem> {
    return filter { it.unreadCount > 0 }
        .sortedByDescending { it.libraryManga.lastRead }
        .take(10)
}

@Composable
internal fun ContinueReadingShelf(
    items: List<LibraryItem>,
    selection: Set<Long>,
    onClick: (LibraryManga) -> Unit,
    onLongClick: (LibraryManga) -> Unit,
    onClickContinueReading: ((LibraryManga) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val shelfItems = remember(items) { items.continueReadingShelfItems() }
    if (shelfItems.isEmpty() || onClickContinueReading == null) return

    Column(modifier = modifier) {
        Text(
            text = stringResource(MR.strings.library_continue_reading_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(
                items = shelfItems,
                key = { it.libraryManga.manga.id },
                contentType = { "library_continue_reading_item" },
            ) { libraryItem ->
                val manga = libraryItem.libraryManga.manga
                Box(modifier = Modifier.width(128.dp)) {
                    MangaCompactGridItem(
                        isSelected = manga.id in selection,
                        title = manga.title,
                        coverData = MangaCover(
                            mangaId = manga.id,
                            sourceId = manga.source,
                            isMangaFavorite = manga.favorite,
                            url = manga.thumbnailUrl,
                            lastModified = manga.coverLastModified,
                        ),
                        coverBadgeStart = {
                            DownloadsBadge(count = libraryItem.badges.downloadCount)
                            UnreadBadge(count = libraryItem.badges.unreadCount)
                        },
                        coverBadgeEnd = {
                            LanguageBadge(
                                isLocal = libraryItem.badges.isLocal,
                                sourceLanguage = libraryItem.badges.sourceLanguage,
                            )
                        },
                        onLongClick = { onLongClick(libraryItem.libraryManga) },
                        onClick = { onClick(libraryItem.libraryManga) },
                        onClickContinueReading = { onClickContinueReading(libraryItem.libraryManga) },
                    )
                }
            }
        }
    }
}
