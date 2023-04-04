package com.example.tap_shop.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.tap_shop.R
import com.example.tap_shop.databinding.ProductDetailsBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductDetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private var count: Int = 0
    private var updatePrice: Float = 0F
    private val viewModel by activityViewModels<HomeViewModel>()
//    private val progressDialog by unsafeLazy { DialogLoader (requireContext()) }
    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val dialogBinding = ProductDetailsBottomSheetDialogBinding.inflate(layoutInflater)

        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val parent = dialogBinding.root.parent as View
        val bottomSheetBehavior = BottomSheetBehavior.from(parent)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        dialogBinding.okayBtn.setOnClickListener {
            dialog.dismiss()
        }

        viewModel.selectedProducts.let {
            dialogBinding.imageArtiest.load(it?.images?.get(0))
            dialogBinding.titleName.text = it?.title
            dialogBinding.priceName.text = "Price: $${it?.price.toString()}"
            dialogBinding.descriptionLabel.text = it?.description.toString()

            dialogBinding.add.setOnClickListener {
                dialogBinding.subs.isEnabled = true
                dialogBinding.checkOutBtn.isEnabled = true
                count += 1
                dialogBinding.totalPriceBox.text = count.toString()
                val first = viewModel.selectedProducts?.price?.toFloat()
                updatePrice = first!! * count.toFloat()
                dialogBinding.totalPrice.text = "TotalPrice: $$updatePrice"
            }

            if (count == 0){
                dialogBinding.subs.isEnabled = false
                dialogBinding.checkOutBtn.isEnabled = false
            }

            dialogBinding.subs.setOnClickListener {
                if (count == 1){
                    dialogBinding.subs.isEnabled = false
                    dialogBinding.checkOutBtn.isEnabled = false
                }
                count -= 1
                dialogBinding.totalPriceBox.text = count.toString()
                updatePrice = updatePrice!! - viewModel.selectedProducts?.price?.toFloat()!!
                dialogBinding.totalPrice.text = "TotalPrice: $$updatePrice"
            }

            dialogBinding.checkOutBtn.setOnClickListener {
                dialogBinding.detailsView.visibility = View.GONE
                dialogBinding.successView.visibility = View.VISIBLE
                dialogBinding.totalPrice2.text = "Total Payable Amount: $$updatePrice"
            }

            dialogBinding.continueBtn.setOnClickListener {
                dialog.dismiss()
            }

        }
        return dialog
    }
    override fun getTheme(): Int = R.style.SheetDialog
}