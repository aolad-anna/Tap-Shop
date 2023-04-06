package com.example.tap_shop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tap_shop.R
import com.example.tap_shop.utils.TopSpacingItemDecoration
import com.example.tap_shop.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var allCategoryAdapter: AllCategoryRecyclerAdapter
    private val viewModel by activityViewModels<HomeViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGetAllCategoriesList()
    }

    private fun getGetAllCategoriesList() {
        viewModel.getAllCategories.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.recyclerview.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
                binding.header.visibility = View.VISIBLE
                binding.recyclerview.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                    val topSpacingDecorator = TopSpacingItemDecoration(20)
                    addItemDecoration(topSpacingDecorator)
                    allCategoryAdapter = AllCategoryRecyclerAdapter()
                    adapter = allCategoryAdapter
                    allCategoryAdapter.submitList(it)

                    allCategoryAdapter.onItemClicked = {
                        viewModel.selectedCategories = it
                        val args = Bundle().apply {
                            putString("id", it.id.toString())
                        }
                        findNavController().navigate(R.id.categoryWiseAllProductFragment, args)
                    }
                    return@Observer
                }
            }
            else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.allCategoriesApiCall()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}