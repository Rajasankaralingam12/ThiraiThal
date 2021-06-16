package com.thiraithal.ui.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.thiraithal.R
import com.thiraithal.databinding.FragmentUploadBinding
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService
import com.thiraithal.ui.base.BaseFragment
import com.thiraithal.ui.home.HomeScreen
import com.thiraithal.ui.upload.uploadViewmodel.UploadViewModel
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModel
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModelFactory
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_test_base.view.*
import kotlinx.android.synthetic.main.fragment_test_base.view.llview
import kotlinx.android.synthetic.main.fragment_upload.*
import kotlinx.android.synthetic.main.fragment_upload.view.*
import java.io.IOException
import java.util.*

class UploadFragment: BaseFragment(){

    private val retrofitService = RetrofitService.getInstance()

    private lateinit var uploadViewModel: UploadViewModel

    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    protected lateinit var uploadBinding: ViewBinding

    override fun onFragmentCreated(view: View) {
    /*    viewModel= ViewModelProvider(this,
            WallpaperViewModelFactory(
                MainRepository(retrofitService)
            )
        ).get(UploadViewModel::class.java)*/

        activity?.let { FirebaseApp.initializeApp(it) }
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference



        uploadBinding.root.image_preview.setOnClickListener {  launchGallery()   }
        uploadBinding.root.btn_upload_image.setOnClickListener { if(validateUpload()){
            uploadImage()
        } }
    }

    override fun onParentFragmentCreated(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: ViewBinding
    ) {
        uploadBinding = FragmentUploadBinding.inflate(layoutInflater)
        mainViewBinding.root.llview.addView(uploadBinding.root)
        showSearchView(false)
    }


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun validateUpload():Boolean{
        if(uploadBinding.root.etUploaderName.text.toString().isEmpty()){
            Toast.makeText(activity, "Please Enter Name", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun uploadImage(){

        if(filePath != null){
            progressBarVisibility(true)

            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addPopularImage(downloadUri.toString())
                    Log.d("Uploaded Image URL", downloadUri.toString())

                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(activity, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    fun addPopularImage(uploadedImageURL: String){


        val uploaderName : String = uploadBinding.root.etUploaderName.text.toString()

        val popularWallpaperModel = PopularWallpaperModel("1",uploaderName,uploadedImageURL,true, "asc")

         uploadViewModel.baseResponse.observe(viewLifecycleOwner, Observer{
             Log.d("TAG", "Image Posted: ${it.responseMessage}")
             progressBarVisibility(false)
             Toast.makeText(activity,"Image uploaded successfully", Toast.LENGTH_SHORT).show()
             refreshWallpaper()
         })

        uploadViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            progressBarVisibility(false)

        })
        uploadViewModel.addPopularImages(popularWallpaperModel)

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {

            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, filePath)
                image_preview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun refreshWallpaper(){
        val intent = Intent(activity, HomeScreen::class.java)
        startActivity(intent)
        activity?.finish()
    }

}