package com.example.contact_21in

fun String.isNameValid(): Boolean {
    return this.length > 4
}

fun String.isEmailValid(): Boolean {
    var regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    return this.matches(regex)
}

fun String.isPhoneValid(): Boolean {
    val phoneRegex = Regex("^\\+?[1-9]\\d{1,14}$")
    return this.matches(phoneRegex)

}