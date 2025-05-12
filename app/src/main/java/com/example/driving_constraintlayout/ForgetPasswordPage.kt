package com.example.driving_constraintlayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme

@Composable
fun ForgetPasswordEmailPage(navController: NavController) {
    val constraints = ConstraintSet {
        val logo = createRefFor("logo")
        val emailText = createRefFor("emailText")
        val emailTextField = createRefFor("emailTextField")
        val submitButton = createRefFor("submitButton")
        val noEmailWrongHint = createRefFor("noEmailWrongHint")
        val unknownAccountWrongHint = createRefFor("unknownAccountWrongHint")
        constrain(logo) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(emailText) {
            top.linkTo(logo.bottom)
            start.linkTo(logo.start, margin = 25.dp)
        }
        constrain(emailTextField) {
            top.linkTo(emailText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(submitButton) {
            top.linkTo(emailTextField.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(noEmailWrongHint) {
            top.linkTo(submitButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(unknownAccountWrongHint) {
            top.linkTo(submitButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    var email by remember { mutableStateOf("") }
    var unknownAccount by remember { mutableStateOf<Boolean>(false) }
    var noEmail by remember { mutableStateOf(false) }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7178B3))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("logo")
        ){
            //返回登入頁按鈕
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                IconButton(onClick = {
                    /* 回LoginPage */
                    navController.navigate("LoginPage")
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "返回登入頁",
                        tint = Color.White
                    )
                }
                Text(
                    text = "返回登入頁",
                    color = White,
                    modifier = Modifier
                        // 點擊文字也能返回
                        .clickable {
                            /* 回LoginPage */
                            navController.navigate("LoginPage")
                        }
                        .padding(top = 10.dp)
                )
            }
            //logo
            Image(
                painter = painterResource(id = R.drawable.driving_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(250.dp)
            )
        }
        //emailText
        Text(
            text = "電子信箱",
            color = White,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .layoutId("emailText")
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
                label = { Text("輸入電子信箱") },
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
        //submitButton
        OutlinedButton(
            onClick = {
                /* 判斷email是否是已註冊帳號，若是，傳更改密碼的介面(?。若否，跳出尚未註冊的提示訊息 */
                noEmail = false
                unknownAccount = false
                var loginAccount = members.filter { it.email == email}
                if (email.isBlank()){
                    noEmail = true
                }else if (loginAccount.isEmpty()) {
                    unknownAccount = true
                }else {
                    navController.navigate("ResetPasswordPage")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7BADC)),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(top = 25.dp)
                .size(width = 150.dp, height = 50.dp)
                .layoutId("submitButton")
        ) {
            Text(
                text = "送出",
                color = Black,
            )
        }
        //noEmailWrongHint、unknownAccountWrongHint
        if (noEmail){
            Text(
                text = "請輸入電子信箱",
                color = Color(0xFFCA0000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .layoutId("noEmailWrongHint")
            )
        }else if (unknownAccount) {
            Text(
                text = "您尚未註冊帳號",
                color = Color(0xFFCA0000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .layoutId("unknownAccountWrongHint")
            )
        }
    }
}

@Composable
fun ResetPasswordPage(navController: NavController) {
    val constraints = ConstraintSet {
        val logo = createRefFor("logo")
        val passwordText = createRefFor("passwordText")
        val passwordTextField = createRefFor("passwordTextField")
        val confirmPasswordText = createRefFor("confirmPasswordText")
        val confirmPasswordTextField = createRefFor("confirmPasswordTextField")
        val updateButton = createRefFor("updateButton")
        val noPasswordWrongHint = createRefFor("noPasswordWrongHint")
        val noConfirmPasswordWrongHint = createRefFor("noConfirmPasswordWrongHint")
        val validPasswordWrongHint = createRefFor("validPasswordWrongHint")
        constrain(logo) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(passwordText) {
            top.linkTo(logo.bottom)
            start.linkTo(logo.start, margin = 25.dp)
        }
        constrain(passwordTextField) {
            top.linkTo(passwordText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(confirmPasswordText) {
            top.linkTo(passwordTextField.bottom, margin = 25.dp)
            start.linkTo(parent.start, margin = 25.dp)
        }
        constrain(confirmPasswordTextField) {
            top.linkTo(confirmPasswordText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(updateButton) {
            top.linkTo(confirmPasswordTextField.bottom, margin = 25.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(noPasswordWrongHint) {
            top.linkTo(updateButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(noConfirmPasswordWrongHint) {
            top.linkTo(updateButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(validPasswordWrongHint) {
            top.linkTo(updateButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmpasswordVisible by remember { mutableStateOf(false) }
    var noPasswordWrongHint by remember { mutableStateOf<Boolean?>(false) }
    var noConfirmPasswordWrongHint by remember { mutableStateOf<Boolean?>(false) }
    var validPassword by remember { mutableStateOf<Boolean?>(true) }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7178B3))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("logo")
        ){
            //返回登入頁按鈕
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                IconButton(onClick = {
                    /* 返回LoginPage */
                    navController.navigate("LoginPage")
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "返回登入頁",
                        tint = Color.White
                    )
                }
                Text(
                    text = "返回登入頁",
                    color = White,
                    modifier = Modifier
                        // 點擊文字也能返回
                        .clickable {
                            /* 返回LoginPage */
                            navController.navigate("LoginPage")
                        }
                        .padding(top = 10.dp)
                )
            }
            //logo
            Image(
                painter = painterResource(id = R.drawable.driving_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(250.dp)
            )
        }
        //passwordText
        Text(
            text = "密碼",
            color = White,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .layoutId("passwordText")
        )
        //passwordTextField
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("輸入新的密碼") },
            modifier = Modifier
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        //confirmPasswordText
        Text(
            text = "確認密碼",
            color = White,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .layoutId("confirmPasswordText")
        )
        //confirmPasswordTextField
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("再次輸入密碼") },
            modifier = Modifier
                .width(350.dp)
                .layoutId("confirmPasswordTextField"),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (confirmpasswordVisible) "🙈" else "👁️"
                IconButton(onClick = { confirmpasswordVisible = !confirmpasswordVisible }) {
                    Text(icon)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        //updateButton
        OutlinedButton(
            onClick = {
                /* 判斷newPassword是否==confirmPassword，若是，把newPassword傳到帳號資料庫取代原本的密碼。若否，跳出confirmPassword != newPassword的提示訊息 */
                noPasswordWrongHint = false
                noConfirmPasswordWrongHint = false
                validPassword = true
                if(newPassword.isBlank()) {
                    noPasswordWrongHint = true
                }
                else if(confirmPassword.isBlank()) {
                    noConfirmPasswordWrongHint = true
                }
                else if(newPassword == confirmPassword) {
                    validPassword = true
                    navController.navigate("LoginPage")
                    /*把newPassword傳到帳號資料庫取代原本的密碼*/
                }else {
                    validPassword = false
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7BADC)),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .layoutId("updateButton")
        ) {
            Text(
                text = "更新",
                color = Black,
            )
        }
        when {
            noPasswordWrongHint == true -> {
                Text(
                    text = "請輸入密碼",
                    color = Color(0xFFCA0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .layoutId("noPasswordWrongHint")
                )
            }
            noConfirmPasswordWrongHint == true -> {
                Text(
                    text = "請輸入確認密碼",
                    color = Color(0xFFCA0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .layoutId("noConfirmPasswordWrongHint")
                )
            }
            validPassword == false -> {
                Text(
                    text = "密碼與確認密碼不一致",
                    color = Color(0xFFCA0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .layoutId("validPasswordWrongHint")
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordEmailPagePreview() {
    Driving_ConstraintLayoutTheme {
        var navController = rememberNavController()
        ForgetPasswordEmailPage(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordPagePreview() {
    Driving_ConstraintLayoutTheme {
        var navController = rememberNavController()
        ResetPasswordPage(navController = navController)
    }
}