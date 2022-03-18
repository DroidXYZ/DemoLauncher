package com.example.homelauncher.ui.main

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homelauncher.R
import com.example.homelauncher.databinding.RowItemInstallAppLayoutBinding
import com.example.homelauncher.model.InstalledAppInfo


class InstalledAppInfoAdapter (val context: Context?): RecyclerView.Adapter<InstalledAppInfoAdapter.BaseViewHolder<*>>(){

    private var installedAppList: ArrayList<InstalledAppInfo> = arrayListOf()
    fun setInstallAppList( installedAppList: ArrayList<InstalledAppInfo> ){
        this.installedAppList = installedAppList
        notifyDataSetChanged()
    }
    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: InstalledAppInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val bindingComponent:RowItemInstallAppLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_install_app_layout, parent, false)
         return ViewHolderInstalledApp(bindingComponent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val installedAppInfo = installedAppList[position]
        holder.bind(installedAppInfo)

    }
    inner class ViewHolderInstalledApp(var binding: RowItemInstallAppLayoutBinding) :
        BaseViewHolder<InstalledAppInfo>(binding.root) {
        override fun bind(item: InstalledAppInfo) {
            binding.clMainLayout.setOnClickListener {
                try {
                    val i = context?.packageManager?.getLaunchIntentForPackage(item.packageName)
                    context?.startActivity(i)
                } catch (e: Exception) {
                   e.printStackTrace()
                }

            }
            binding.ivAppIcon.setImageDrawable(item.appIcon)
            binding.tvAppName.text = item.appName
            binding.tvPackageName.text = item.packageName
            binding.tvClassName.text = item.appActivityClassName
            binding.tvVersionName.text = item.appVersionName
            binding.tvVersionCode.text = item.appVersionCode
        }
    }

    override fun getItemCount(): Int {
        return  installedAppList.size
    }
}