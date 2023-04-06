package com.example.tap_shop.ui.profile


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.tap_shop.databinding.FragmentProfileBinding
import java.util.*


class ProfileFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE   = 1
    private val GALLERY_REQUEST  = 188
    private val MY_CAMERA_PERMISSION_CODE   = 100
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gallery.setOnClickListener {
            if (checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_CAMERA_PERMISSION_CODE)
            } else {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
            }
        }

        binding.camera.setOnClickListener {
            if (checkSelfPermission(requireContext(),Manifest.permission.CAMERA) != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val REQUEST_IMAGE_CAPTURE = 1
        val GALLERY_REQUEST=10

        if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == AppCompatActivity.RESULT_OK) when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                binding.user.setImageBitmap(imageBitmap)
            }
            GALLERY_REQUEST -> {
                val Bitmap = data?.extras?.get("data") as Bitmap
                binding.user.setImageBitmap(Bitmap)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}