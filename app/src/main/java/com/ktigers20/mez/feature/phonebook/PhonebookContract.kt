package com.ktigers20.mez.feature.phonebook

import com.ktigers20.mez.data.entity.PhoneBookInfo

interface PhonebookContract {
    interface View {
        fun setPhoneBookList(phoneBookList: ArrayList<PhoneBookInfo>)
        fun setPhoneBookPageIsEnd(isEnd: Boolean)
    }

    interface Presenter {
        val view: View

        fun getPhoneBookList()
    }
}