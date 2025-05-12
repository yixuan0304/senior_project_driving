package com.example.driving_constraintlayout

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.driving_constraintlayout.ui.theme.Driving_ConstraintLayoutTheme

@Composable
fun RegisterPage(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var read_selectedOption by remember { mutableStateOf(false) }
    var showTermsOfServiceDialog by remember { mutableStateOf(false) }
    var showPrivacyPolicyDialog by remember { mutableStateOf(false) }
    var identity_selectedOption by remember { mutableStateOf<String>("Public") }
    var noEmail by remember { mutableStateOf(false) }
    var noPassword by remember { mutableStateOf(false) }
    var noConfirmPassword by remember { mutableStateOf(false) }
    var noPhoneNumber by remember { mutableStateOf(false) }
    var noReadSelectedOption by remember { mutableStateOf(false) }
    var isRegisterButtonClicked by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7178B3))
    ){
        Box(
            modifier = Modifier.fillMaxWidth()
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //電子信箱
            Text(
                text = "電子信箱",
                color = White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 25.dp)
            )
            if(isRegisterButtonClicked){
                if (noEmail) {
                    Text(
                        text = "請輸入電子信箱",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }else if(!isValidEmail(email)){
                    Text(
                        text = "電子信箱格式錯誤",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //輸入新密碼
            Text(
                text = "密碼",
                color = White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 25.dp, top = 25.dp)
            )
            if(isRegisterButtonClicked){
                if (noPassword) {
                    Text(
                        text = "請輸入密碼",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp)
                    )
                }
            }
        }

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("輸入密碼") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp),
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //確認新密碼
            Text(
                text = "確認密碼",
                color = White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 25.dp, top = 25.dp)
            )
            if(isRegisterButtonClicked){
                if (noConfirmPassword) {
                    Text(
                        text = "請確認密碼",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp)
                    )
                }else if(password != confirmPassword){
                    Text(
                        text = "密碼與確認密碼不一致",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp)
                    )
                }
            }

        }

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("再次輸入密碼") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp),
            singleLine = true,
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (confirmPasswordVisible) "🙈" else "👁️"
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Text(icon)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //手機號碼
            Text(
                text = "手機號碼",
                color = White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 25.dp, top = 25.dp)
            )
            if(isRegisterButtonClicked){
                if (noPhoneNumber) {
                    Text(
                        text = "請輸入手機號碼",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp)
                    )
                }else if(!isValidPhoneNumber(phoneNumber)){
                    Text(
                        text = "手機號碼格式錯誤",
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp)
                    )
                }
            }
        }

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("輸入手機號碼") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(350.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        //是否閱讀服務條款、隱私權保護政策
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = read_selectedOption == true, //當 read_selectedOption 的值為 true 時，selected 設為 true
                onClick = { read_selectedOption = !read_selectedOption }
            )
            Text("我已閱讀並同意 ", color = Color.White)
            Text(
                text = "服務條款",
                color = Color.Yellow,
                modifier = Modifier.clickable { showTermsOfServiceDialog = true }
            )
            Text(" 及 ", color = Color.White)
            Text(
                text = "隱私權保護政策",
                color = Color.Yellow,
                modifier = Modifier.clickable { showPrivacyPolicyDialog = true }
            )
        }

        if(isRegisterButtonClicked){
            if (noReadSelectedOption) {
                Text(
                    text = "請閱讀並同意服務條款及隱私權保護政策",
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
            }
        }

        // 選擇身分別
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("選擇身分別(必填)", color = Color.White)
            RadioButton(
                selected = identity_selectedOption == "Public",
                onClick = { identity_selectedOption = "Public" }
            )
            Text("民眾", color = Color.White)

            RadioButton(
                selected = identity_selectedOption == "Police",
                onClick = { identity_selectedOption = "Police" }
            )
            Text("警方", color = Color.White)
        }

        //註冊按鈕
        OutlinedButton(
            onClick = {
                isRegisterButtonClicked = true
                /* 確認上述資料格式等是否正確 */
                //Email格式
                if (email == ""){
                    Log.d("emailtest","noemail")
                    noEmail = true
                } else {
                    noEmail = false
                    if (isValidEmail(email)) {
                        Log.d("emailtest", "emailtrue")
                    } else if (!isValidEmail(email)) {
                        Log.d("emailtest", "emailfalse")
                    }
                }
                //密碼、確認密碼是否一致
                if(password == ""){
                    Log.d("passwordtest","nopassword")
                    noPassword = true
                } else {
                    noPassword = false
                }
                if(confirmPassword == ""){
                    Log.d("passwordtest","nopassword")
                    noConfirmPassword = true
                } else {
                    noConfirmPassword = false
                    if (password == confirmPassword) {
                        Log.d("passwordtest", "passwordtrue")
                    } else if (password != confirmPassword) {
                        Log.d("passwordtest", "passwordfalse")
                    }
                }
                //電話格式
                if(phoneNumber == ""){
                    Log.d("phonetest","nophone")
                    noPhoneNumber = true
                }else {
                    noPhoneNumber = false
                    if (isValidPhoneNumber(phoneNumber)) {
                        Log.d("phonetest", "phonetrue")
                    } else if (!isValidPhoneNumber(phoneNumber)) {
                        Log.d("phonetest", "phonefalse")
                    }
                }
                //是否閱讀服務條款、隱私權保護政策
                if (read_selectedOption){
                    Log.d("readselectedtest","readselectedtrue")
                    noReadSelectedOption = false
                }else if (!read_selectedOption){
                    Log.d("readselectedtest","readselectedfalse")
                    noReadSelectedOption = true
                }
                //所有格式正確
                if(!noEmail && isValidEmail(email) &&
                    !noPassword && isValidPhoneNumber(phoneNumber) &&
                    !noConfirmPassword &&
                    !noPhoneNumber &&
                    !noReadSelectedOption
                ){
                    navController.navigate("LoginPage")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA7BADC)),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp)
                .size(width = 180.dp, height = 50.dp)
        ) {
            Text(
                text = "註冊",
                color = Black,
            )
        }

    }
}

//EmailRegex
fun isValidEmail(email: String):Boolean{
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(email)
}

//PhoneRegex
fun isValidPhoneNumber(phoneNumber: String):Boolean{
    val phoneNumberRegex = Regex("^09\\d{8}$")
    return phoneNumberRegex.matches(phoneNumber)
}


@Preview(showBackground = true)
@Composable
fun RegisterPagePreview(){
    Driving_ConstraintLayoutTheme{
        var navController = rememberNavController()
        RegisterPage(navController = navController)
    }
}