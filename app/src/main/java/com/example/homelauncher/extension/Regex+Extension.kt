package com.example.homelauncher.extension

import kotlin.text.Regex

fun Regex.filterSearch(inputText:String): Boolean {
    return this.containsMatchIn(inputText)
}