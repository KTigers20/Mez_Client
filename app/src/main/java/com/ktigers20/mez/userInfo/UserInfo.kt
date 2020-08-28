package com.ktigers20.mez.userInfo

object UserInfo {
    var myUId = ""
    var isAdditionalInfoNeeded = ""
    var mainPageMode = MainDashBoardMode.MY
    var isAlarmOff = false
}

enum class MainDashBoardMode {
    MY, ALL
}
