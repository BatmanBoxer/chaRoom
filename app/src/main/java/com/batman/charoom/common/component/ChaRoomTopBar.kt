package com.batman.charoom.common.component

/**
 * Created by Pronay Sarker on 04/10/2024 (6:11 PM)
 */

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.batman.charoom.common.objects.ChaRoomIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChaRoomTopAppBar(
    topBarTitle: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topBarTitle,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconBox(
                icon = ChaRoomIcons.ArrowBack,
                onClick = onNavigateBack,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        actions = actions,
        modifier = modifier,
    )
}