package com.batman.charoom.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.commandiron.compose_loading.ChasingTwoDots
import com.commandiron.compose_loading.DoubleBounce

/**
 * Created by Pronay Sarker on 04/10/2024 (8:32 PM)
 */
@Composable
fun ChaRoomLoadingWheel(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        DoubleBounce(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}