package cn.bproject.neteasynews.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.base.BaseFragment


class AboutFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentInteractionListener?.onFragmentTitleChange("关于应用")
    }



    override fun getLayoutResId(): Int {
        return R.layout.fragment_about
    }

    override fun initData() {
    }


}
