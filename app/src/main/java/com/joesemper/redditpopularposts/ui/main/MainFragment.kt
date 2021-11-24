package com.joesemper.redditpopularposts.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joesemper.redditpopularposts.databinding.FragmentMainBinding
import com.joesemper.redditpopularposts.ui.main.adapters.MainRvAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

class MainFragment : Fragment(), AndroidScopeComponent {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val scope: Scope by fragmentScope()
    private val viewModel: MainViewModel by viewModel()

    private lateinit var postsAdapter: MainRvAdapter

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
    }

    private fun initRv() {
        lifecycleScope.launchWhenStarted {
            viewModel.currentPosts.collect {
                postsAdapter = MainRvAdapter(it)
                binding.rvPosts.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = postsAdapter
                }
            }

        }

    }



}