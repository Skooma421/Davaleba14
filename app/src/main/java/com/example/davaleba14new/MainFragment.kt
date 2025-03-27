package com.example.dessertapp

import BaseFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.davaleba14new.Dessert
import com.example.davaleba14new.R
import com.example.davaleba14new.databinding.FragmentMainBinding
import com.example.myapp.DessertAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val dessertViewModel: DessertViewModel by viewModels()
    private lateinit var adapter: DessertAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DessertAdapter { dessert ->
            dessertViewModel.removeDessert(dessert)
            Toast.makeText(requireContext(), "${dessert.name} removed", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter

        binding.btnOne.setOnClickListener {
            dessertViewModel.addDessert("Chocolate Cake", R.drawable.chocolate_cake)
        }

        binding.btnTwo.setOnClickListener {
            dessertViewModel.addDessert("Strawberry Cheesecake", R.drawable.cheesecake)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            dessertViewModel.refreshDesserts()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dessertViewModel.desserts.collect { items ->
                    adapter.submitList(items)
                }
            }
        }
    }
}
