package com.example.driving_constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Driving_ConstraintLayoutTheme {
                var navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.statusBars // 讓 Scaffold 考慮狀態列
                ) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // 讓內容避開狀態列
                    ) {
                        NavHost(navController = navController, startDestination = "LoadingPage") {
                            composable("LoadingPage") {
                                LoadingPage(navController)
                            }
                            composable("LoginPage") {
                                LoginPage(navController)
                            }
                            composable("ForgetPasswordEmailPage") {
                                ForgetPasswordEmailPage(navController)
                            }
                            composable("ResetPasswordPage") {
                                ResetPasswordPage(navController)
                            }
                            composable("RegisterPage") {
                                RegisterPage(navController)
                            }
                            composable("PoliceIncidentManagementPage") {
                                PoliceIncidentManagementPage(navController)
                            }
                            composable("PublicHomePage") {
                                PublicHomePage(navController)
                            }
                            composable("CameraPreviewScreen") {
                                CameraPreviewScreen(navController)
                            }
                            composable("PublicDrunkDrivingHistoryPage") {
                                PublicDrunkDrivingHistoryPage(navController)
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