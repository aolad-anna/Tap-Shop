package com.example.tap_shop.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.tap_shop.R
import com.example.tap_shop.databinding.BottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductDetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<HomeViewModel>()
//    private val progressDialog by unsafeLazy { DialogLoader (requireContext()) }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val dialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)

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
            dialogBinding.descriptionLabel.text = "${it?.description.toString()}"

            dialogBinding.playBtn.setOnClickListener {
//                val args = bundleOf(
//                    "album" to albumTG,
//                    "title" to titleTG,
//                    "artistDisplayName" to artistDisplayNameTG,
//                    "urlPlayable" to urlPlayable,
//                )
//                findNavController().navigate(
//                    R.id.playerViewFragment,
//                    args
//                )
            }

        }
        return dialog
    }
    override fun getTheme(): Int = R.style.SheetDialog
}