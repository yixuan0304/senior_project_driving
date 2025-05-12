package com.example.driving_constraintlayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme

@Composable
fun LoginPage(navController: NavController){
    val constraints = ConstraintSet {
        val logo = createRefFor("logo")
        val emailTextField = createRefFor("emailTextField")
        val passwordTextField = createRefFor("passwordTextField")
        val forgetPasswordButton = createRefFor("forgetPasswordButton")
        val emailPasswordWrongHint = createRefFor("emailPasswordWrongHint")
        val loginButton = createRefFor("loginButton")
        val quickLoginLine = createRefFor("quickLoginLine")
        val googleLoginButton = createRefFor("googleLoginButton")
        val facebookLoginButton = createRefFor("facebookLoginButton")
        val emailLoginButton = createRefFor("emailLoginButton")
        val orLine = createRefFor("orLine")
        val registerButton = createRefFor("registerButton")
        constrain(logo) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(emailTextField) {
            top.linkTo(logo.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(passwordTextField) {
            top.linkTo(emailTextField.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(forgetPasswordButton) {
            top.linkTo(passwordTextField.bottom)
            end.linkTo(passwordTextField.end)
        }
        constrain(emailPasswordWrongHint) {
            top.linkTo(passwordTextField.bottom)
            end.linkTo(forgetPasswordButton.start)
        }
        constrain(loginButton) {
            top.linkTo(forgetPasswordButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(quickLoginLine) {
            top.linkTo(loginButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(googleLoginButton) {
            top.linkTo(quickLoginLine.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(facebookLoginButton) {
            top.linkTo(googleLoginButton.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(emailLoginButton) {
            top.linkTo(facebookLoginButton.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(orLine) {
            top.linkTo(emailLoginButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(registerButton) {
            top.linkTo(orLine.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var unknownAccount by remember { mutableStateOf<Boolean>(false) }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7178B3))
    ) {
        //logo
        Image(
            painter = painterResource(id = R.drawable.driving_logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(250.dp)
                .layoutId("logo")
        )
        //emailTextField
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("emailTextField")
        ){
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("電子信箱") },
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Icon(
                Icons.Rounded.Email,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 300.dp)
                    .size(35.dp)
            )
        }
        //passwordTextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密碼") },
            modifier = Modifier
                .padding(top = 25.dp)
                .width(350.dp)
                .layoutId("passwordTextField"),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) "🙈" else "👁️"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(icon)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        //forgetPasswordButton
        TextButton(
            onClick = {
                /* 跳到ForgetPasswordPage */
                navController.navigate("ForgetPasswordEmailPage")
            },
            modifier = Modifier
                .layoutId("forgetPasswordButton")
        ) {
            Text(
                text = "忘記密碼？",
                color = Color.White
            )
        }
        //emailPasswordWrongHint
        if(unknownAccount){
            Text(
                text = "電子信箱或密碼錯誤",
                color = Color(0xFFCA0000),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .layoutId("emailPasswordWrongHint")
            )
        }
        //loginButton
        OutlinedButton(
            onClick = {
                /* 判斷帳號密碼是否正確，再根據使用者帳號判斷要進到警方系統還是民眾系統 */
                var loginAccount = members.filter { it.email == email && it.password == password}
                if (loginAccount.isEmpty()) {
                    unknownAccount = true
                } else if (loginAccount[0].LoginClass == "Police") {
                    navController.navigate("PoliceIncidentManagementPage")
                } else if(loginAccount[0].LoginClass == "Public"){
                    navController.navigate("PublicHomePage")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7BADC)),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 50.dp)
                .layoutId("loginButton")
        ) {
            Text(
                text = "登入",
                color = Black,
            )
        }
        //quickLoginLine
        Box(
            Modifier
                .layoutId("quickLoginLine")
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                thickness = 3.dp
            )
            Box(
                modifier = Modifier
                    .size(width = 75.dp, height = 50.dp)
                    .background(Color(0xFF7178B3))
                    .align(Alignment.Center)
            )
            Text(
                "快速登入",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        //googleLoginButton
        OutlinedButton(
            onClick = { /* https://developer.android.com/identity/sign-in/credential-manager-siwg?hl=zh-tw */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 50.dp)
                .layoutId("googleLoginButton")
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Google登入",
                    color = Black,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
        //facebookLoginButton
        OutlinedButton(
            onClick = { /* https://happyphoebe40090.medium.com/%E9%9A%A8%E6%89%8B%E5%B0%8F%E8%A8%98%E4%B8%80%E4%B8%8B-%E5%9C%A8android-%E4%B8%8A%E9%96%8B%E7%99%BCfacebook-%E7%99%BB%E5%85%A5-5d5cc19615a3 */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 50.dp)
                .layoutId("facebookLoginButton")
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.facebook_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Facebook登入",
                    color = Black,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
        //emailLoginButton
        OutlinedButton(
            onClick = { /* https://medium.com/@maggie.kuo/%E4%BD%BF%E7%94%A8-android-studio-%E9%96%8B%E7%99%BC-firebase-%E5%BF%AB%E9%80%9F%E5%B0%8E%E5%85%A5-email-and-password-authentication-%E8%A8%AD%E5%AE%9A%E4%B8%A6%E5%AF%A6%E4%BD%9C%E7%99%BB%E5%85%A5%E5%8A%9F%E8%83%BD-138d65a20c07 */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 50.dp)
                .layoutId("emailLoginButton")
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.email_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Email登入",
                    color = Black,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
        //orLine
        Box(
            Modifier
                .layoutId("orLine")
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                thickness = 3.dp
            )
            Box(
                modifier = Modifier
                    .size(width = 75.dp, height = 50.dp)
                    .background(Color(0xFF7178B3))
                    //.background(Color.Black)
                    .align(Alignment.Center)
            )
            Text(
                "OR",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        //registerButton
        OutlinedButton(
            onClick = {
                /* 跳到RegisterPage */
                navController.navigate("RegisterPage")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7BADC)),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 180.dp, height = 50.dp)
                .layoutId("registerButton")
        ) {
            Text(
                text = "註冊新帳號",
                color = Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    Driving_ConstraintLayoutTheme {
        var navController = rememberNavController()
        LoginPage(navController = navController)
    }
}