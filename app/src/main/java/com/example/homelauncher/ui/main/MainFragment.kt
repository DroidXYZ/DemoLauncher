package com.example.homelauncher.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homelauncher.R
import com.example.homelib.LauncherInfo

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

//    private lateinit var viewModel: MainViewModel
    private lateinit var rvAppInfo:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        rvAppInfo = view.findViewById(R.id.rvAppInfo)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        rvAppInfo.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context) as androidx.recyclerview.widget.RecyclerView.LayoutManager?
        val infoList= LauncherInfo(context).getAllInstallAppInfo()
        val installedAppInfoAdapter= InstalledAppInfoAdapter(context,infoList)
        rvAppInfo.adapter = installedAppInfoAdapter
        installedAppInfoAdapter.notifyDataSetChanged()

    }

}