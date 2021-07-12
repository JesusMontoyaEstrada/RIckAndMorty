package com.example.rickandmortypractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortypractice.databinding.FragmentCharactersBinding
import com.example.rickandmortypractice.presentation.MainActivity
import com.example.rickandmortypractice.presentation.adapter.CharacterAdapter
import com.example.rickandmortypractice.presentation.adapter.CharacterLoadStateAdapter
import com.example.rickandmortypractice.presentation.viewmodel.CharacterViewModels
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private lateinit var viewModel : CharacterViewModels
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding : FragmentCharactersBinding
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharactersBinding.bind(view)
        viewModel = (activity as MainActivity).characterViewModels
        characterAdapter = (activity as MainActivity).characterAdapter
        characterAdapter.setOnClickListener { character ->
            val bundle = Bundle().apply {
                putSerializable("character", character)
            }

            findNavController().navigate(
                R.id.action_charactersFragment_to_characterDetailFragment,
                bundle
            )
        }

        initAdapter()
        search()
        initSearch()

        binding.retryButton.setOnClickListener { characterAdapter.retry() }

    }

    private fun initAdapter(){


            binding.rvCharacter.layoutManager = LinearLayoutManager(activity)

            binding.rvCharacter.adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadStateAdapter { characterAdapter.retry() },
                footer = CharacterLoadStateAdapter { characterAdapter.retry() }
            )

            characterAdapter.addLoadStateListener { loadState ->

                val isListEmpty = loadState.refresh is LoadState.NotLoading && characterAdapter.itemCount == 0
                showEmptyList(isListEmpty)

                binding.rvCharacter.isVisible = loadState.source.refresh is LoadState.NotLoading

                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        activity,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

    }

    private fun showEmptyList(listEmpty: Boolean) {
        if (listEmpty) {
            binding.emptyList.visibility = View.VISIBLE
            binding.rvCharacter.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.rvCharacter.visibility = View.VISIBLE
        }
    }

    private fun initSearch(query: String? = null) {

        binding.svCharacters.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                updateRepoListFromInput(query)
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = MainScope().launch {
                    delay(2000)
//                    updateRepoListFromInput(newText)
                    search(newText)
                }
                return false
            }

        })

        binding.svCharacters.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
//                updateRepoListFromInput()
                search()
                return false
            }

        })

        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            characterAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvCharacter.scrollToPosition(0) }
        }
    }

    private fun search(query: String? = null) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getCharacters(query).collectLatest{
                characterAdapter.submitData(it)
            }
        }
    }
}