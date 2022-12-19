package com.shazdroid.spacexapp.view.fragments.rocket_list

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.base.BaseFragment
import com.shazdroid.spacexapp.databinding.FragmentRocketListBinding
import com.shazdroid.spacexapp.utility.ResultData
import com.shazdroid.spacexapp.view.adapter.RocketListAdapter
import com.shazdroid.spacexapp.viewmodels.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : BaseFragment<FragmentRocketListBinding>() {
    override fun getFragmentLayout() = R.layout.fragment_rocket_list

    private val viewModel by viewModels<RocketViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        fetchRocket()
        onRocketClickListener()
        onBackPressHandler()
    }

    /**
     * Observe response from repository
     * If there is no `Internet Connection`
     * We will fetch data from Room db else from `Network`
     */
    fun fetchRocket() {
        viewModel.getRocketList().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultData.Loading -> {
                    viewModel.isLoading.postValue(true)
                    viewModel.isException.postValue(false)
                }
                is ResultData.Success -> {
                    viewModel.isLoading.postValue(false)
                    viewModel.isException.postValue(false)
                    binding.recyclerView.adapter?.let {
                        (it as RocketListAdapter).setData(result.data)
                    }
                }
                is ResultData.Exception -> {
                    viewModel.isLoading.postValue(false)
                    viewModel.isException.postValue(true)
                }
            }
        }
    }

    /**
     * OnItemClick navigate to `Rocket Detail Page`
     * @argument id: String
     */
    private fun onRocketClickListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.onItemClickEvent.collect { id ->
                findNavController().navigate(R.id.rocketDetailFragment, Bundle().apply { putString("id", id) })
            }
        }
    }

    /**
     * Try to load data again
     */
    override fun onResume() {
        super.onResume()
        viewModel.getRocketList()
    }

    /**
     * On back press to exit application
     */
    private fun onBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}