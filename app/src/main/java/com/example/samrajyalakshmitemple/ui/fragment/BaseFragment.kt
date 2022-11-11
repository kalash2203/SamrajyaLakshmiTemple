package com.example.samrajyalakshmitemple.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.utils.SavedPrefManager

abstract class BaseFragment<T: ViewBinding>  : Fragment() {
    protected var binding: T? = null
    open lateinit var savedPrefManager: SavedPrefManager


    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T
    val repository = Repository()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = bindingInflater.invoke(inflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedPrefManager = SavedPrefManager(requireContext())
        setup()
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
    fun Fragment.toast(message:String?){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()

    }
    open fun logoutDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("LogOut")
        alertDialog.setMessage("Do you want to logout?")
        alertDialog.setNegativeButton(
            "Cancel"
        ) { _, _ ->

        }
        alertDialog.setPositiveButton("Yes"){ _,_->
            savedPrefManager.putLogin(false)
            toast("Logout Successful")
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}