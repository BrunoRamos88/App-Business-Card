package com.br.appbusinesscard2.ui.addcard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.appbusinesscard2.databinding.FragmentAddBusinesscardBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCardFragment : Fragment() {

    private val mAddCardViewModel: AddCardViewModel by viewModel()
    private val binding: FragmentAddBusinesscardBinding by lazy {
        FragmentAddBusinesscardBinding.inflate(layoutInflater)
    }

    private val arguments by navArgs<AddCardFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments.businessCard.let {
            if (it != null) {
                mAddCardViewModel.receivedCard(it)
            }
        }

        binding.navController = findNavController()
        binding.lifecycleOwner = viewLifecycleOwner

        mAddCardViewModel.errorSnackbar.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                Log.i("BusinessCard", "Snackbar message: $it" )
            }
        }

        binding.viewModel = mAddCardViewModel

        initNavigateHomeObserver()
        return binding.root
    }

    private fun initNavigateHomeObserver() {
        mAddCardViewModel.newCard.observe(viewLifecycleOwner) {
            if (it != null) {
                navigateToHomeFragment()
            }
        }
    }

    private fun navigateToHomeFragment() {
        findNavController().popBackStack()
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddCardFragment().apply {  }
    }
}