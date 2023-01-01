package com.example.samrajyalakshmitemple.ui.fragment
import ShowUserPanelRecordViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samrajyalakshmitemple.R
import com.example.samrajyalakshmitemple.databinding.FragmentUserPanelBinding
import com.example.samrajyalakshmitemple.ui.adapter.UserPanelAdapter
import com.example.samrajyalakshmitemple.utils.ChangeRoleInterface
import com.example.samrajyalakshmitemple.viewModelFactory.ShowUserPanelRecordViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class UserPanelFragment :  BaseFragment<FragmentUserPanelBinding>(),ChangeRoleInterface {
    private val userPanelViewModel by viewModels<ShowUserPanelRecordViewModel> {
        ShowUserPanelRecordViewModelFactory(repository)
    }
    val TAG="UserPanelFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserPanelBinding
            = FragmentUserPanelBinding::inflate


    override fun setup() {
userPanelViewModel.showUserPanelRecordResponse()
    userPanelViewModel.showUserPanelRecordLiveData.observe(viewLifecycleOwner, Observer {
        val userPanelAdapter=UserPanelAdapter(requireContext(),it.data,this)
        val linearLayoutManager=LinearLayoutManager(requireContext())
        binding?.rvUserPanel?.layoutManager=linearLayoutManager
        binding?.rvUserPanel?.adapter=userPanelAdapter
    })
        userPanelViewModel.makeAdminLiveData.observe(viewLifecycleOwner, Observer {
            if(it.message=="admin make success"){
                toast("Role Updated")
                userPanelViewModel.showUserPanelRecordResponse()
            }
        })
        userPanelViewModel.removeUserLiveData.observe(viewLifecycleOwner, Observer {
            if (it.message=="user deleted success"){
                toast("User Removed")
                userPanelViewModel.showUserPanelRecordResponse()

            }
        })
        val view5 = requireActivity().findViewById<MaterialButton>(R.id.login_signout_btn)
        view5?.setOnClickListener {
            if (!savedPrefManager.isLogin()){
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_user_panel_to_login)
            }else{
                savedPrefManager.putLogin(false)
                savedPrefManager.putToken("")
                FirebaseAuth.getInstance().signOut()
                hideItem()
            }
        }
    }

    override fun onChangeRole(role: String, email: String?) {
        userPanelViewModel.changeRole(email,role)
    }

    override fun onRemoveUser(id: String?) {
        userPanelViewModel.removeUserResponse(id)
    }
}