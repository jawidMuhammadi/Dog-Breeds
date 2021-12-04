package com.spotlightapps.mydog.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.spotlightapps.mydog.adapter.DogImageAdapter
import com.spotlightapps.mydog.data.ApiCallStatus
import com.spotlightapps.mydog.databinding.FragmentDogListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DogListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null
    private val viewModel: DogListViewModel by viewModels()

    @Inject
    private lateinit var adapter: DogImageAdapter

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
        viewModel.getBreedList()
        setObservers()

        binding.spBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Do Nothing
            }

        }
    }

    private fun setObservers() {
        viewModel.breedList.observe(viewLifecycleOwner, { breedList ->
            val breedsName: List<String?>? = breedList?.map { list -> list.name }
            breedsName?.let { setBreedsSpinnerAdapter(it) }
        })

        viewModel.apiCallStatus.observe(viewLifecycleOwner, {
            when (it) {
                ApiCallStatus.PROGRESS -> binding.progressBar.visibility = View.VISIBLE
                ApiCallStatus.SUCCESS -> binding.progressBar.visibility = View.GONE
                else -> binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun setBreedsSpinnerAdapter(breeds: List<String?>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_item,
            breeds
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spBreeds.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}