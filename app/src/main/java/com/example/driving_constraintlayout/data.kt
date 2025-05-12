package com.example.driving_constraintlayout

import androidx.compose.ui.graphics.Color

data class CaseData(
    val id: String,
    val time: String,
    val location: String,
    val level: String,
    val latitude_longitude: String,
    val report_account:String
){
    override fun toString(): String {
        return "$id $time $location $level"
    }
}

data class MemberData(
    val mId : Int,
    val email: String,
    val password: String,
    val phone:String,
    val LoginClass: String
)

data class GoogleAccount(
    val token:String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)

//之後連資料庫
var cases = mutableListOf<CaseData>(
    CaseData("A30250001", "113/09/21 16:22:29", "高雄市鳳山區光復路二段132號", "疑似酒駕", "22.630908634497704, 120.34361266886201", "a1113302@mail.nuk.edu.tw"),
    CaseData("A30250055", "113/10/22 18:11:02", "臺南市六甲區中山路212號", "疑似酒駕", "23.23220393334596, 120.34688391119965", "a1113306@mail.nuk.edu.tw"),
    CaseData("A30270049", "113/11/02 08:15:02", "新竹市民族路40之1號", "疑似酒駕", "24.805344979651906, 120.97328447075684", "a1113313@mail.nuk.edu.tw"),
    CaseData("A30270095", "113/11/05 12:19:04", "台北市信義區市府路1號", "高風險", "25.037382874045484, 121.56451994006987", "a1113322@mail.nuk.edu.tw"),
    CaseData("A30270026", "113/11/09 11:22:38", "高雄市路竹區中山路1821號", "高風險", "22.84227359110479, 120.26647262653874", "a1113323@mail.nuk.edu.tw"),
    CaseData("A30270076", "113/11/08 10:22:08", "臺北市中正區徐州路5號", "中高風險", "25.042317003776944, 121.51955402657912", "a1113352@mail.nuk.edu.tw")
)

val members = mutableListOf(
    MemberData(1, "a1113302@mail.nuk.edu.tw", "123", "0912111111","Public"),
    MemberData(2, "a1113306@mail.nuk.edu.tw", "123", "0922222222","Public"),
    MemberData(3, "a1113313@mail.nuk.edu.tw", "123", "0933333333","Public"),
    MemberData(123, "123", "123", "0933333333","Public"),
    MemberData(4, "a1113322@mail.nuk.edu.tw", "456", "0944444444","Police"),
    MemberData(5, "a1113323@mail.nuk.edu.tw", "456", "0955555555","Police"),
    MemberData(6, "a1113352@mail.nuk.edu.tw", "456", "0966666666","Police"),
    MemberData(7, "m1123306@mail.nuk.edu.tw", "456", "0977777777","Police"),
    MemberData(456, "456", "456", "0933333333","Police"),
)

fun getColorFormLevel (level: String): Color {
    return when(level){
        "疑似酒駕" -> Color(0xFFFF0000)
        "高風險" -> Color(0xFFFFFF00)
        "中高風險" -> Color(0xFFFFA500)
        else -> Color(0xFF00FF00)
    }
}