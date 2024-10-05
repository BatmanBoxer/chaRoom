package com.batman.charoom.features.features_authentication.presentation.screens.signup_page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.batman.charoom.common.component.ChaRoomLoadingWheel
import com.batman.charoom.common.component.ChaRoomTopAppBar
import com.batman.charoom.common.dataClass.SignUpData
import com.batman.charoom.features.features_authentication.presentation.screens.components.InputField
import com.batman.charoom.navigation.NavLogInScreen

@Composable
fun SignUpScreenRoute(
    modifier: Modifier = Modifier,
    viewmodel: SignupViewmodel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit
) {
    val uiState by viewmodel.signupUiState.collectAsStateWithLifecycle()

    SignUpScreen(
        uiState = uiState,
        navigateToLoginScreen = navigateToLoginScreen,
        signUp = viewmodel::signup
    )
}

@Composable
fun SignUpScreen(
    uiState: SignupUiState,
    navigateToLoginScreen: () -> Unit,
    signUp: (SignUpData) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ChaRoomTopAppBar(topBarTitle = "SignUp", onNavigateBack = { navigateToLoginScreen() })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {

            SignUpScreen(
                navigateToLoginScreen = navigateToLoginScreen,
                signUp = signUp
            )

            when (uiState) {
                SignupUiState.Initial -> Unit

                is SignupUiState.ShowErrorToast -> {
                    Toast.makeText(context, uiState.error, Toast.LENGTH_SHORT).show()
                }

                SignupUiState.ShowProgress -> {
                    ChaRoomLoadingWheel()
                }

                SignupUiState.Success -> {
                    Toast.makeText(context, "Account created successfully. Please login", Toast.LENGTH_SHORT).show()
                    navigateToLoginScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignUpScreen(
    navigateToLoginScreen: () -> Unit,
    signUp: (SignUpData) -> Unit
) {
    var agreeToTerms by remember { mutableStateOf(false) }

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
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Sign Up to create a new account",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(50.dp))

            InputField(icon = Icons.Default.AccountCircle, placeholder = "Name") { }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(icon = Icons.Default.Email, placeholder = "Email") { }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(icon = Icons.Default.Lock, placeholder = "Password") { }
            Spacer(modifier = Modifier.height(10.dp))
            InputField(icon = Icons.Default.Lock, placeholder = "Confirm Password") { }

            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Checkbox(
                        checked = agreeToTerms,
                        onCheckedChange = { agreeToTerms = it }
                    )
                    FlowRow {
                        Text(
                            text = "By Clicking this I agree to accept All the ",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Term and Condition",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(55.dp),
                onClick = {
                    // add validation
                    signUp(
                        SignUpData(
                            name = "",
                            email = "",
                            password = "",
                        )
                    )
                },
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyLarge
                )
            }


        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            Text(text = "Don't Have an account?")
            Text(
                text = "Log In",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        navigateToLoginScreen()
                    }
            )
        }
    }
}