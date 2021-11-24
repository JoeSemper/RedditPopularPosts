package com.joesemper.redditpopularposts.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joesemper.redditpopularposts.databinding.FragmentMainBinding
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

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }



}