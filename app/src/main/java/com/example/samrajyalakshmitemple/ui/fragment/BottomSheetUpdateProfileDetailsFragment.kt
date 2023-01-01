package com.example.samrajyalakshmitemple.ui.fragment
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.samrajyalakshmitemple.databinding.FragmentBottomSheetUpdateProfileDetailsBinding
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.utils.Constants
import com.example.samrajyalakshmitemple.utils.GlideLoader
import com.example.samrajyalakshmitemple.utils.SavedPrefManager
import com.example.samrajyalakshmitemple.viewModelFactory.UpdateViewModelFactory
import com.example.samrajyalakshmitemple.viewmodels.UpdateProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class BottomSheetUpdateProfileDetailsFragment : BottomSheetDialogFragment() {
         var binding : FragmentBottomSheetUpdateProfileDetailsBinding? = null

private var uploadedImageURL:String?=""
    val repository = Repository()
     lateinit var savedPrefManager: SavedPrefManager
    private var headerImageLauncher: ActivityResultLauncher<Intent>? = null
    private var permissions = arrayOf(

        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private var headerImagePart: MultipartBody.Part? = null

    private val updateProfileViewModel by viewModels<UpdateProfileViewModel>{
        UpdateViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      binding =  FragmentBottomSheetUpdateProfileDetailsBinding.inflate(
            inflater,
            container,
            false
        )


        binding?.btnSave?.setOnClickListener {
          updateProfileDetail()
        }


        headerImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    val imageUri = intent!!.data
                    val imageProjection = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = requireContext().contentResolver.query(
                        imageUri!!,
                        imageProjection,
                        null,
                        null,
                        null
                    )!!
                    cursor.moveToFirst()
                    val indexImage = cursor.getColumnIndex(imageProjection[0])
                    val imagePath = cursor.getString(indexImage)
                    cursor.close()
                    val imageFile = File(imagePath)
                    val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    headerImagePart = MultipartBody.Part.createFormData(
                        "profileImage",
                        imageFile.name,
                        requestBody
                    )

                   uploadImage()

                    GlideLoader(requireContext()).loadUserPicture(imageFile, binding?.imageView)

                } else {
                    Log.e("Error", "Profile Image change error")
                }
            }


        binding?.editProfileImage?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permissions[0]
                ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    requireContext(),
                    permissions[1]
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getImage()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), permissions,
                    PERMISSION
                )
            }
        }

        savedPrefManager = SavedPrefManager(requireContext())
        binding!!.edtName.setText(arguments?.getString(Constants.NAME))
        binding!!.edtNumber.setText(arguments?.getString(Constants.NUMBER))
        binding!!.edtCity.setText(arguments?.getString(Constants.CITY))
        binding!!.edtCountry.setText(arguments?.getString(Constants.COUNTRY))
        binding!!.edtState.setText(arguments?.getString(Constants.STATE))


updateProfileViewModel.uploadImageLiveData.observe(viewLifecycleOwner, Observer {
    uploadedImageURL=it.data?.url
})

        return  binding?.root


    }
private fun uploadImage()
{
    updateProfileViewModel.uploadImageResponse(headerImagePart)
}

    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        headerImageLauncher?.launch(intent)
    }

    companion object {
        private const val PERMISSION = 999
    }


    private fun updateProfileDetail() {
        Log.d("image", headerImagePart?.headers.toString())
        updateProfileViewModel.editProfileResponse(savedPrefManager.getEmail()!!,
            binding?.edtName?.text.toString().trim(),
           savedPrefManager.getEmail(),
            binding?.edtNumber?.text.toString().trim(),
            binding?.edtCity?.text.toString().trim(),
            binding?.edtState?.text.toString().trim(),
            binding?.edtCountry?.text.toString().trim(),
            uploadedImageURL
            )


        updateProfileViewModel.editProfileLiveData.observe(viewLifecycleOwner, Observer {
            if (it.message=="user updated success"){
                Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
                dismiss()
            }

        })

        }



    }


