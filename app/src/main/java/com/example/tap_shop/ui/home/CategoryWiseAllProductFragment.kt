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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tap_shop.R
import com.example.tap_shop.utils.TopSpacingItemDecoration
import com.example.tap_shop.databinding.FragmentCategoryWiseAllProductBinding


class CategoryWiseAllProductFragment : Fragment() {
    private var id: String? = null
    private lateinit var allCategoryWiseProductAdapter: CategoryWiseProductRecyclerAdapter
    private val viewModel by activityViewModels<HomeViewModel>()
    private var _binding: FragmentCategoryWiseAllProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryWiseAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allProductsList()
    }

    private fun allProductsList() {
        id = arguments?.getString("id")
        viewModel.getProducts.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.recyclerview.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
                binding.header.visibility = View.VISIBLE
                binding.title.text = it[0].category?.name
                binding.recyclerview.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    val topSpacingDecorator = TopSpacingItemDecoration(20)
                    addItemDecoration(topSpacingDecorator)
                    allCategoryWiseProductAdapter = CategoryWiseProductRecyclerAdapter()
                    adapter = allCategoryWiseProductAdapter
                    allCategoryWiseProductAdapter.submitList(it)

                    allCategoryWiseProductAdapter.onItemClicked = {
                        viewModel.selectedProducts = it
                        findNavController().navigate(R.id.categoryBottomSheetDialog)
                    }
                    return@Observer
                }
            }
            else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        id?.let { viewModel.allProductsApiCall(it) }
    }
}