package dev.ptit.ui.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.data.news.NewsModel
import dev.ptit.databinding.FragmentNewsBinding
import dev.ptit.ui.adapter.home.NewsVPAdapter
import dev.ptit.ui.adapter.news.NewsRVAdapter
import dev.ptit.ui.adapter.news.NewsTagRVAdapter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()
    private var newsTagAdapter: NewsTagRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsTagAdapter = NewsTagRVAdapter{
            viewModel.setSelectedTag(it)
        }

        setUpData()
        initView()
        initListener()

    }

    private fun setUpData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedTag.collect {
                    newsTagAdapter?.setSelectedTag(it)
                }
            }

        }
    }

    private fun initView() {
        binding.rvNewsTag.adapter = newsTagAdapter?.apply {
            setList(viewModel.getAllTags())
        }

        binding.rvNews.adapter = NewsRVAdapter{
            findNavController().navigate(R.id.action_newsFragment_to_newsDetailsFragment)
        }.apply {
            setList(viewModel.getAllNews())
        }
    }

    private fun initListener() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}