package com.example.homelauncher.model

import android.graphics.drawable.Drawable
import android.os.Parcelable

data class InstalledAppInfo (
    val  appName:String,
    val  packageName:String,
    val  appIcon: Drawable?,
    val  appActivityClassName:String?,
    val  appVersionCode:String,
    val  appVersionName:String,
    )