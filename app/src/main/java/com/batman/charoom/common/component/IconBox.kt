package com.batman.charoom.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batman.charoom.common.objects.ChaRoomIcons
import com.batman.charoom.ui.theme.ChaRoomTheme

@Composable
fun IconBox(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedIconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.outlinedIconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
        )
    }
}

@Composable
@Preview
private fun IconBoxPreview(
    modifier: Modifier = Modifier,
) {
    ChaRoomTheme {
        Surface {
            IconBox(
                icon = ChaRoomIcons.ArrowBack,
                modifier = modifier,
                onClick = {},
            )
        }
    }
}