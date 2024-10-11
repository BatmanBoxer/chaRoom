package com.batman.charoom.features.feature_profile.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.batman.charoom.R
import com.batman.charoom.common.component.ChaRoomErrorScreen
import com.batman.charoom.common.component.ChaRoomLoadingWheel
import com.batman.charoom.common.component.ChaRoomTopAppBar
import com.batman.charoom.common.dataClass.UserData
import com.batman.charoom.features.feature_profile.domain.model.User

/**
 * Created by Pronay Sarker on 07/10/2024 (7:35 PM)
 */
@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToChatScreen :()->Unit = {},
) {
    val viewmodel: ProfileViewmodel = hiltViewModel()
    val uiState by viewmodel.profileUiState.collectAsStateWithLifecycle()

    //we are not using launched effect to load initial data. intial data will be laoded from viewmodle so we can test it dufing test cases
    ProfileScreen(
        uiState = uiState,
        navigateBack = navigateBack,
        onRetry = viewmodel::fetchUserData,
        onMessageClick = {viewmodel.addUserToChat()}
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    navigateBack: () -> Unit,
    onRetry: () -> Unit,
    onMessageClick: () -> Unit
) {
    Scaffold(
        topBar = { ChaRoomTopAppBar(topBarTitle = "Profile", onNavigateBack = navigateBack) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (uiState) {
                is ProfileUiState.Error -> {
                    ChaRoomErrorScreen(
                        title = uiState.message,
                        onRetry = onRetry
                    )
                }

                ProfileUiState.Loading -> {
                    ChaRoomLoadingWheel()
                }

                is ProfileUiState.Success -> {
                    ProfileContent(userData = uiState.userData, onMessageClick = onMessageClick)
                }
            }
        }

    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    userData: User,
    //make this button take a is user boolean so that we can show the message button only if the user is not the current user
    //also make this route take a id:String so we can see if the load the users acourding to thier uid


    onMessageClick: () -> Unit  // Action for the Message button
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Profile Image with Edit Icon
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            if (userData.imgUrl != null) {
                AsyncImage(
                    model = userData.imgUrl,
                    contentDescription = "profileImage",
                    modifier = Modifier
                        .size(150.dp)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.empty_placeholder),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
            }

            if (userData.isLocalUser == true) {
                IconButton(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.7f), CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                        .align(Alignment.BottomEnd)
                        .size(40.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Change photo",
                        tint = Color.White
                    )
                }
            }

            if (!userData.isLocalUser!!) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(40.dp)
                        .background(Color.Black.copy(alpha = 0.7f), CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                    onClick = onMessageClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Message",
                        tint = Color.White
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4C3)), // Light background
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                UserDataRow(
                    title = "Name",
                    data = userData.name ?: ""
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserDataRow(
                    title = "Email",
                    data = userData.email ?: ""
                )
            }
        }
    }
}

@Composable
fun UserDataRow(
    title: String,
    data: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = data,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.Gray.copy(alpha = 0.5f),
            thickness = 0.5.dp
        )
    }
}

