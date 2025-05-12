package com.example.driving_constraintlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme
import kotlinx.coroutines.delay

@Composable
fun LoadingPage(navController: NavController){
    val constraints = ConstraintSet {
        val logo = createRefFor("logo")
        val loadingText = createRefFor("loadingText")
        val guideline = createGuidelineFromTop(0.2f)
        constrain(logo){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(loadingText){
            top.linkTo(logo.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7178B3))
    ) {
        Image(
            painter = painterResource(id = R.drawable.driving_logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(400.dp)
                .layoutId("logo")
        )
        Text(
            text = "加載中...",
            color = White,
            fontSize = 30.sp,
            modifier = Modifier.layoutId("loadingText")
        )
    }
    // 延遲3秒後跳轉到login
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("LoginPage")
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPagePreview() {
    Driving_ConstraintLayoutTheme {
        var navController = rememberNavController()
        LoadingPage(navController = navController)
    }
}