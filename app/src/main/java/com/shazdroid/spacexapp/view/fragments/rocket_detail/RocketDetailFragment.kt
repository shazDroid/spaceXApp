package com.shazdroid.spacexapp.view.fragments.rocket_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.base.BaseFragment
import com.shazdroid.spacexapp.databinding.FragmentRocketDetailBinding
import com.shazdroid.spacexapp.utility.ResultData
import com.shazdroid.spacexapp.utility.loadImage
import com.shazdroid.spacexapp.view.adapter.RocketImageListAdapter
import com.shazdroid.spacexapp.viewmodels.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RocketDetailFragment : BaseFragment<FragmentRocketDetailBinding>() {
    override fun getFragmentLayout() = R.layout.fragment_rocket_detail
    private val args by navArgs<RocketDetailFragmentArgs>()
    private val viewModel by viewModels<RocketViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.fragment = this

        fetchRocketDetail(args.id)
        observerOnImageSelect()
    }

    /**
     * Fetch rocket detail either from room or api
     */
    private fun fetchRocketDetail(id: String) {
        viewModel.getRocketDetail(id).observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultData.Exception -> { // doesn't require to implement as we are already caching details
                }
                is ResultData.Loading -> { // doesn't require to implement as we are already caching details
                }
                is ResultData.Success -> { // doesn't require to implement as we are already caching details
                    binding.data = result.data
                    binding.horizontalRecyclerView.adapter?.let {
                        (it as RocketImageListAdapter).setData(result.data?.flickrImages)
                    }
                }
            }
        }
    }

    /**
     * Observe image selection so we can render it as main image
     * By default first image in imageList is as display image
     */
    private fun observerOnImageSelect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onImageSelectEvent.collect { url ->
                binding.displayImageView.loadImage(url)
            }
        }
    }

    /**
     * Open wikipedia page once link clicked
     */
    fun openWikipedia(url: String){
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


}