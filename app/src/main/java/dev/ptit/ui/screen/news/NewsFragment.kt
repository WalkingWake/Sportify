package dev.ptit.ui.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentNewsBinding
import dev.ptit.ui.adapter.news.NewsRVAdapter
import dev.ptit.ui.adapter.news.NewsTagRVAdapter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by activityViewModels()
    private var newsTagAdapter: NewsTagRVAdapter? = null
    private var newsRVAdapter: NewsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsTagAdapter = NewsTagRVAdapter {
            viewModel.setSelectedTag(it)
        }

        newsRVAdapter = NewsRVAdapter {
            viewModel.setSelectedNews(it)
            findNavController().navigate(R.id.action_newsFragment_to_newsDetailsFragment)
        }

        setUpData()
        initView()
        initListener()

    }

    private fun setUpData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedTag.collect {
                newsTagAdapter?.setSelectedTag(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.news.collect{
                newsRVAdapter?.setList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tags.collect {
                newsTagAdapter?.setList(it)
            }
        }
    }

    private fun initView() {
        binding.rvNewsTag.adapter = newsTagAdapter
        binding.rvNews.adapter = newsRVAdapter

    }

    private fun initListener() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}