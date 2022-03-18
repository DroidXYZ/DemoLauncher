package com.example.homelauncher.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.homelauncher.R
import com.example.homelauncher.extension.filterSearch
import com.example.homelauncher.model.InstalledAppInfo
import com.example.homelib.LauncherInfo


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var rvAppInfo:RecyclerView
    private lateinit var searchView: SearchView
    val installedAppList= arrayListOf<InstalledAppInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        rvAppInfo = view.findViewById(R.id.rvAppInfo)
        searchView = view.findViewById(R.id.searchView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvAppInfo.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context) as androidx.recyclerview.widget.RecyclerView.LayoutManager?
        val dividerItemDecoration = DividerItemDecoration(
            rvAppInfo.context,
            1
        )
        rvAppInfo.addItemDecoration(dividerItemDecoration)
        val infoList= LauncherInfo.getAllInstallAppInfo(context)
        infoList.forEach {
            installedAppList.add(InstalledAppInfo(it.appName,it.packageName,it.iconURL,it.appActivityClassName,it.appVersionCode,it.appVersionName))
        }
        val installedAppInfoAdapter= InstalledAppInfoAdapter(context)
        installedAppInfoAdapter.setInstallAppList(installedAppList)
        rvAppInfo.adapter = installedAppInfoAdapter
        installedAppInfoAdapter.notifyDataSetChanged()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchCriteria: String): Boolean {
                filterList(searchCriteria,installedAppInfoAdapter)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText,installedAppInfoAdapter)
                return false
            }
        })
    }

    private fun filterList(searchCriteria:String,installedAppInfoAdapter:InstalledAppInfoAdapter){
        val pattern = Regex("\\b$searchCriteria[a-zA-Z]*\\b", RegexOption.IGNORE_CASE)
        val filteredMap = installedAppList.filter {s->
            pattern.filterSearch(s.appName?:"")
        }
        installedAppInfoAdapter.setInstallAppList(filteredMap.toMutableList() as ArrayList<InstalledAppInfo>)
    }



}


