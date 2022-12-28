import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.EditProfileResponse
import com.example.samrajyalakshmitemple.models.RemoveUserResponse
import com.example.samrajyalakshmitemple.models.ShowMyProfileResponse
import com.example.samrajyalakshmitemple.models.UploadImageResponse
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.viewmodels.MyViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ShowMyProfileViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val showMyProfileLiveData: LiveData<ShowMyProfileResponse>
        get() = repo.showMyProfileResponse



    fun showMyProfileResponse(email: String?)
    {
        viewModelScope.launch {
            repo.showMyProfileResponse(email)
        }
    }



}