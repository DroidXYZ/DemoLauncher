package com.example.homelib

import android.graphics.drawable.Drawable

data class InstalledAppModel (
    val  appName:String,
    val  packageName:String,
    val  iconURL:Drawable?,
    val  appActivityClassName:String,
    val  appVersionCode:String,
    val  appVersionName:String,
    )