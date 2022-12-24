import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.ShowMyProfileResponse
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.viewmodels.MyViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowMyProfileViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val showMyProfileLiveData: LiveData<ShowMyProfileResponse>
        get() = repo.showMyProfileResponse

    fun ShowMyProfileResponse(email: String)
    {
        viewModelScope.launch {
            repo.showMyProfileResponse(email)
        }
    }
}