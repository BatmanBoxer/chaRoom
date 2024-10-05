package com.batman.charoom.features.features_authentication.presentation.screens.login_page

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batman.charoom.R
import com.batman.charoom.common.component.ChaRoomLoadingWheel
import com.batman.charoom.common.component.ChaRoomTopAppBar
import com.batman.charoom.features.features_authentication.presentation.screens.components.InputField

@Composable
fun LoginScreenRoute(
    modifier: Modifier = Modifier,
    navigateToHomeScreen: () -> Unit,
    navigateToSignupScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = loginUiState,
        navigateToSignupScreen = navigateToSignupScreen,
        navigateToHomeScreen = navigateToHomeScreen,
        onLoginClick = viewModel::login
    )
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    navigateToHomeScreen: () -> Unit,
    navigateToSignupScreen: () -> Unit,
    onLoginClick: (email: String, password: String) -> Unit
) {
    val context = LocalContext.current
    var showExitAppDialog by rememberSaveable { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

    if(showExitAppDialog){
        ExitAppDialog { userChoise ->
            if(userChoise){
                activity?.finish()
            } else{
                showExitAppDialog = false
            }
        }
    }

    Scaffold(
        topBar = {
            ChaRoomTopAppBar(topBarTitle = "Login", onNavigateBack = { showExitAppDialog = true })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LoginScreenContent(
                navigateToSignupScreen = navigateToSignupScreen, onLoginClick = onLoginClick
            )

            when (uiState) {
                LoginUiState.Initial -> Unit

                LoginUiState.ShowProgress -> ChaRoomLoadingWheel()

                is LoginUiState.ShowValidationErrorString -> {
                    Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
                }

                LoginUiState.Success -> {
                    navigateToHomeScreen()
                }
            }
        }

    }
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navigateToSignupScreen: () -> Unit,
    onLoginClick: (email: String, password: String) -> Unit
) {
    var rememberPassword by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Sign In To Continue",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(50.dp))
            InputField(icon = Icons.Default.Email, placeholder = "Email") { }
            Spacer(modifier = Modifier.height(20.dp))
            InputField(icon = Icons.Default.Lock, placeholder = "Password") { }

            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = rememberPassword,
                        onCheckedChange = { rememberPassword = it })
                    Text(
                        text = "Remember me",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        // Handle forgot password action
                    })
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(55.dp),
                onClick = { onLoginClick("email", "password") },
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Sign In", style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Or", style = MaterialTheme.typography.labelLarge
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue.copy(alpha = 0.8f),
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    onClick = {
                        // Handle Sign In From Facebook
                    },
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Facebook",
                            tint = Color.Unspecified,
                        )
                        Text(
                            text = "Facebook",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f), onClick = {
                        // Handle Sign In From Google
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.8f),
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ), shape = RoundedCornerShape(15.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google",
                            tint = Color.Unspecified
                        )
                        Text(
                            text = "Google",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            Text(text = "Don't Have an account?")
            Text(text = "Sign Up",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { navigateToSignupScreen() })
        }
    }
}

@Composable
fun ExitAppDialog(
    modifier: Modifier = Modifier,
    userSelection: (Boolean) -> Unit
) {
    AlertDialog(
        icon = { Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "exit app") },
        text = { Text(text = "Are you sure you want to exit the app?") },
        onDismissRequest = { userSelection(false) },
        confirmButton = {
            TextButton(onClick = { userSelection(true) }) {
                Text(text = "Exit", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = { userSelection(false) }) {
                Text(text = "Cancel")
            }
        },
    )
}