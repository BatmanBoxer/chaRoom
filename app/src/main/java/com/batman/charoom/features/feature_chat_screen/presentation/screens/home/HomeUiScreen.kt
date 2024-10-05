package com.batman.charoom.features.feature_chat_screen.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.batman.charoom.R
import com.batman.charoom.common.component.ChaRoomErrorScreen
import com.batman.charoom.common.component.ChaRoomLoadingWheel
import com.batman.charoom.features.feature_chat_screen.domain.model.RecentChat
import com.batman.charoom.common.objects.ChaRoomIcons

/**
 * Created by Pronay Sarker on 04/10/2024 (9:44 PM)
 */
@Composable
fun HomeUiScreenRoute(
    viewmodel: HomeViewmodel = hiltViewModel(),
    navigateToChatScreen : ( ) -> Unit
) {
    val uiState by  viewmodel.homeUiState.collectAsStateWithLifecycle()

    HomeUiScreen(
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUiScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    var showMoreMenu by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (isSearchActive) {
                        // implement search box
                    } else {
                        Text(text = "Chats")
                    }
                },
                navigationIcon = {
                    if (!isSearchActive) {
                        IconButton(onClick = { isSearchActive = !isSearchActive }) {
                            Icon(
                                imageVector = ChaRoomIcons.Search,
                                contentDescription = "Search chats"
                            )
                        }
                    }
                },
                actions = {
                    if (!isSearchActive) {
                        IconButton(onClick = { showMoreMenu = !showMoreMenu }) {
                            Icon(imageVector = ChaRoomIcons.MoreVert, contentDescription = "More")
                        }
                    }

                    if (showMoreMenu) {
                        DropdownMenu(
                            expanded = showMoreMenu,
                            onDismissRequest = { showMoreMenu = false }) {
                            DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { })
                            DropdownMenuItem(text = { Text(text = "Licences") }, onClick = { })
                            DropdownMenuItem(
                                text = { Text(text = "About Developers") },
                                onClick = { })
                        }
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (uiState) {
                is HomeUiState.Error -> {
                    ChaRoomErrorScreen(title = uiState.message)
                }

                HomeUiState.Loading -> {
                    ChaRoomLoadingWheel()
                }

                is HomeUiState.Success -> {
                    HomeScreenContent(
                        chats = uiState.chats
                    )
                }

                HomeUiState.Initial -> Unit
            }
        }
    }
}


@Composable
fun HomeScreenContent(
    chats: List<RecentChat>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        items(chats.size) { index ->
            ChatListItem(chat = chats[index])
            if (index < chats.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
fun ChatListItem(
    chat: RecentChat,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
    ) {
        ImageItem(
            chat.profileImageUrl
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = 0.15.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = chat.timestamp,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
fun ImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
//    Image(
//        painter = painterResource(id = R.drawable.empty_placeholder), contentDescription = "",
//        contentScale = ContentScale.Crop,
//        modifier = modifier
//            .size(56.dp)
//            .clip(CircleShape)
//            .background(Color.Gray)
//    )

    /**
     * currently AsyncImage is taking too much time to load images + freezes, we have to fix that.
     */
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.empty_placeholder),
        fallback = painterResource(id = R.drawable.empty_placeholder),
        error = painterResource(id = R.drawable.error),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRecentChatsScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenContent(chats = sampleChats)
    }

}





