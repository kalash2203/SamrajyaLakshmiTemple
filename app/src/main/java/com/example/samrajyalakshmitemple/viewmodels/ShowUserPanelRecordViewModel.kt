import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.samrajyalakshmitemple.models.MakeAdminResponse
import com.example.samrajyalakshmitemple.models.RemoveUserResponse
import com.example.samrajyalakshmitemple.models.ShowUserPanelRecordResponse
import com.example.samrajyalakshmitemple.repository.Repository
import com.example.samrajyalakshmitemple.viewmodels.MyViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowUserPanelRecordViewModel @Inject constructor (val repo: Repository
): MyViewModel() {

    val showUserPanelRecordLiveData: LiveData<ShowUserPanelRecordResponse>
        get() = repo.showUserPanelRecordResponse

    fun showUserPanelRecordResponse()
    {
        viewModelScope.launch {
            repo.showUserPanelRecordResponse()
        }
    }
    val makeAdminLiveData: LiveData<MakeAdminResponse>
        get() = repo.makeAdminResponse

    fun makeAdminResponse(email:String,role:String) {
        viewModelScope.launch {
            repo.makeAdminResponse(email,role)
        }
    }
    val removeUserLiveData: LiveData<RemoveUserResponse>
        get() = repo.removeUserResponse

    fun removeUserResponse(id:String)
    {
        viewModelScope.launch {
            repo.removeUserResponse(id)
        }
    }
}