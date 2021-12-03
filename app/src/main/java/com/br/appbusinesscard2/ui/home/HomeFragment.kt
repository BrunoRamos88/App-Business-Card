package com.br.appbusinesscard2.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.appbusinesscard2.R
import com.br.appbusinesscard2.databinding.HomeFragmentBinding
import com.br.appbusinesscard2.domain.BusinessCard
import com.br.appbusinesscard2.ui.adapter.BusinessCardAdapter
import com.br.appbusinesscard2.ui.adapter.BusinessCardListener
import com.br.appbusinesscard2.util.Image
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment
    }

    private val mHomeViewModel: HomeViewModel by viewModel()
    private val binding: HomeFragmentBinding by lazy{HomeFragmentBinding.inflate(layoutInflater)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.viewModel = mHomeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initBusinessCardList()
        initNavigateToAddCardObserver()
        return binding.root
    }

    private fun initBusinessCardList() {
        mHomeViewModel.filteredListBusinessCard.observe(viewLifecycleOwner) {
            val adapter = BusinessCardAdapter(BusinessCardListener(clickListener = { businessCard ->
                navigateToEditCardFragment(businessCard)
            }, longClickListener = {businessCard ->
                showRemoveDialog(businessCard)
                true
            }, shareBtnClickListener = {
                context?.let { context -> Image.share(context, it) }
            }))

            initSearchBar()

            binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.homeRecyclerView.adapter = adapter
            adapter.addHeadersAndSubmitList(it)
        }
    }

    private fun initSearchBar() {
        binding.homeSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mHomeViewModel.setSearchQuery(it)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    mHomeViewModel.setSearchQuery(it)
                    return true
                }
                return false
            }
        })
    }

    private fun initNavigateToAddCardObserver() {
        mHomeViewModel.navigateToAddCardFragment.observe(viewLifecycleOwner) { navigateToAddCardFragment ->
            if (navigateToAddCardFragment == true) {
                navigateToAddCardFragment()
                mHomeViewModel.doneNavigateToAddCardFragment()
            }
        }
    }

    private fun showRemoveDialog(businessCard: BusinessCard) {
        AlertDialog.Builder(activity)
            .setTitle(getString(R.string.dialog_title_delete))
            .setMessage(getString(R.string.dialog_delete_msg))
            .setNegativeButton(getString(R.string.label_no), null)
            .setPositiveButton(getString(R.string.label_yes), { _, _ ->
                mHomeViewModel.remove(businessCard)
            })
            .create()
            .show()
    }

    private fun navigateToAddCardFragment() {
        val direction = HomeFragmentDirections.navToAddCard(null)
        findNavController().navigate(direction)
    }

    private fun navigateToEditCardFragment(businessCard: BusinessCard) {
        val direction = HomeFragmentDirections.navToAddCard(businessCard)
        findNavController().navigate(direction)
    }
}