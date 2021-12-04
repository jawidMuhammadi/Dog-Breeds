package com.spotlightapps.mydog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.spotlightapps.mydog.databinding.FragmentDogListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DogListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null
    private val viewModel: DogListViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.breedList.observe(viewLifecycleOwner, {
            Toast.makeText(context, "Size = ${it?.size}", Toast.LENGTH_LONG).show()
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getBreedList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}