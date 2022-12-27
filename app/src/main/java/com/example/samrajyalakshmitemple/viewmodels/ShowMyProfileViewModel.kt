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

    val uploadImageLiveData: LiveData<UploadImageResponse>
        get() = repo.uploadImageResponse

    val editProfileLiveData: LiveData<EditProfileResponse>
    get()= repo.editProfileResponse

    fun showMyProfileResponse(email: String?)
    {
        viewModelScope.launch {
            repo.showMyProfileResponse(email)
        }
    }

    fun uploadImageResponse(image:MultipartBody.Part)
    {
        viewModelScope.launch {
            repo.uploadImageResponse(image)
        }
    }
    fun editProfileResponse(email2:String, name: RequestBody, email: RequestBody, phone: RequestBody, city: RequestBody, state: RequestBody, country: RequestBody, img:MultipartBody.Part)
    {
        viewModelScope.launch {
            repo.editProfileResponse(email2, name, email, phone, city, state, country, img)
        }
    }
}