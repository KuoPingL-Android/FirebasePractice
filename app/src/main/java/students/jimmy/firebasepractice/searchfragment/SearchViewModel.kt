package students.jimmy.firebasepractice.searchfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import students.jimmy.firebasepractice.CurrentUser
import students.jimmy.firebasepractice.dataclass.User
import students.jimmy.firebasepractice.dataclass.UserKeys
import students.jimmy.firebasepractice.firebase.Firebase

class SearchViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun searchUser(email: String?) {
        _errorMessage.value = null
        if (email == null) {
            _errorMessage.value = "Please input valid user email"
            return
        }
        getUserInfoByEmail(email)
    }

    private fun getUserInfoByEmail(email: String) {
        Firebase.userRef.whereEqualTo(UserKeys.Email.string, email)
            .get().addOnCompleteListener {task ->
                if (task.isSuccessful) {

                    val r = task.result

                    val obj = task.result?.toObjects(User::class.java)

                    _errorMessage.value = "Found ${obj?.size} results"

                    if (obj?.size == 0) {
                        _user.value = null
                    } else {
                        obj?.first()?.let { user ->
                            _user.value = user
                        }
                    }


                } else {
                    _user.value = null
                }
            }
    }

    fun resetMessage() {
        _errorMessage.value = null
    }
}