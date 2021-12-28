package com.spotlightapps.mydog.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.spotlightapps.mydog.adapter.DogImageAdapter
import com.spotlightapps.mydog.databinding.FragmentDogListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DogListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null
    private val viewModel: DogListViewModel by viewModels()
    private val adapter = DogImageAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDogImage.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .map { it.isFetchingData }
                    .collect {
                        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                    }
            }
        }

        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.map { it.errorMessage }
//                    .collect {
//                        if (it?.isNotEmpty() == true)
//                            Toast.makeText(
//                                context,
//                                "Error: ${it.subSequence(0, it.length / 2)}",
//                                Toast.LENGTH_LONG
//                            ).show()
//                    }
//            }
        }

        binding.spBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getDogsImageList(position)
                adapter.submitList(emptyList())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Do Nothing
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@BindingAdapter(value = ["breedItems"])
fun spinnerItems(spinner: Spinner, breeds: List<String?>?) {
    breeds ?: return
    val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
        spinner.context,
        R.layout.simple_spinner_item,
        breeds
    )
    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}

@BindingAdapter(value = ["dogPicItems"])
fun dogPicItems(recyclerView: RecyclerView, breeds: List<String?>?) {
    breeds ?: return
    (recyclerView.adapter as DogImageAdapter).apply {
        submitList(breeds)
    }
}