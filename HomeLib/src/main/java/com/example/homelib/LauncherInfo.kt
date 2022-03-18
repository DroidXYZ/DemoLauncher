package com.example.homelib

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build


class LauncherInfo constructor(val context: Context?) {

    fun  getAllInstallAppInfo(): ArrayList<InstalledAppModel> {
        val infos: List<ApplicationInfo> =
            context?.packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)?: arrayListOf()
        val apps = arrayListOf<InstalledAppModel>()
        infos.forEach {
            var versionName:String = ""
            var versionCode:String = ""

            val appIcon = context?.packageManager?.getApplicationIcon(it)
            try {
                val pInfo = context?.packageManager?.getPackageInfo(it.packageName, 0)
                 versionName = pInfo?.versionName?:""
                versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    pInfo?.longVersionCode.toString()
                }else{
                    pInfo?.versionCode.toString()
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            val installedAppInfo= InstalledAppModel(it.name,it.packageName,appIcon,it.className,versionCode,versionName)
            apps.add(installedAppInfo)
        }
        return apps
    }
}