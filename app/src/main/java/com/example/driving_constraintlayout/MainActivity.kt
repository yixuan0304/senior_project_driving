package com.example.driving_constraintlayout

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.profile.ProfileScreen
import com.example.driving_constraintlayout.sign_in.GoogleSignIn
import com.example.driving_constraintlayout.sign_in.SignInScreen
import com.example.driving_constraintlayout.sign_in.SignInViewModel
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleSignIn by lazy {
        GoogleSignIn(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Driving_ConstraintLayoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.statusBars // 讓 Scaffold 考慮狀態列
                ) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // 讓內容避開狀態列
                    ) {
                        val navController = rememberNavController()
//                        NavHost(navController = navController, startDestination = "LoadingPage") {
//                            composable("LoadingPage") {
//                                LoadingPage(navController)
//                            }
//                            composable("LoginPage") {
//                                LoginPage(navController)
//                            }
//                            composable("ForgetPasswordEmailPage") {
//                                ForgetPasswordEmailPage(navController)
//                            }
//                            composable("ResetPasswordPage") {
//                                ResetPasswordPage(navController)
//                            }
//                            composable("RegisterPage") {
//                                RegisterPage(navController)
//                            }
//                            composable("PoliceIncidentManagementPage") {
//                                PoliceIncidentManagementPage(navController)
//                            }
//                            composable("PublicHomePage") {
//                                PublicHomePage(navController)
//                            }
//                            composable("CameraPreviewScreen") {
//                                CameraPreviewScreen(navController)
//                            }
//                            composable("PublicDrunkDrivingHistoryPage") {
//                                PublicDrunkDrivingHistoryPage(navController)
//                            }
                        NavHost(navController = navController, startDestination = "sign_in") {
                            composable("sign_in") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = Unit) {
                                    if(googleSignIn.getSignedInUser() != null) {
                                        navController.navigate("profile")
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if(result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleSignIn.signInWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSignInSuccessful) {
                                    if(state.isSignInSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate("profile")
                                        viewModel.resetState()
                                    }
                                }

                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleSignIn.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }
//                                    onSignInClick = {
//                                        lifecycleScope.launch {
//                                            try {
//                                                Log.d("SignIn", "按鈕被點擊")
//                                                val signInIntentSender = googleSignIn.signIn()
//                                                if (signInIntentSender == null) {
//                                                    Toast.makeText(applicationContext, "無法取得登入意圖", Toast.LENGTH_SHORT).show()
//                                                    return@launch
//                                                }
//                                                launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
//                                            } catch (e: Exception) {
//                                                e.printStackTrace()
//                                                Toast.makeText(applicationContext, "登入過程發生錯誤: ${e.message}", Toast.LENGTH_LONG).show()
//                                            }
//                                        }
//                                    }

                                )
                            }
                            composable("profile") {
                                ProfileScreen(
                                    userData = googleSignIn.getSignedInUser(),
                                    onSignOut = {
                                        lifecycleScope.launch {
                                            googleSignIn.signOut()
                                            Toast.makeText(
                                                applicationContext,
                                                "Signed out",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            navController.popBackStack()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Driving_ConstraintLayoutTheme {

    }
}