package com.example.samachar.news

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.samachar.databinding.FragmentSamacharBinding


@Suppress("DEPRECATION")
class SamacharFragment : Fragment() {

    private val viewModel: SamacharViewModel by lazy {
        ViewModelProvider(this).get(SamacharViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSamacharBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = SamacharAdapter(SamacharAdapter.OnClickListener {
            val builder = CustomTabsIntent.Builder()
            val colorInt: Int = Color.parseColor("#F8BBD0")
            builder.setToolbarColor(colorInt)
            val customTabsIntent = builder.build()
            this.context?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(it.url)) }
        })

        binding.refreshLayout.setOnRefreshListener() {
            viewModel.getSamachar("in", "your_API_Key")
            binding.refreshLayout.isRefreshing = false
        }

        binding.samacharList.adapter = adapter

        /*viewModel.samachars.observe(this.viewLifecycleOwner, Observer {

            it?.let {
                adapter.submitList(it)
            }
        })*/

        return binding.root
    }
}