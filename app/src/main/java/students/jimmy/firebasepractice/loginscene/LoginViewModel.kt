package students.jimmy.firebasepractice.loginscene

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import students.jimmy.firebasepractice.MainViewModel
import students.jimmy.firebasepractice.TAG
import students.jimmy.firebasepractice.dataclass.User
import students.jimmy.firebasepractice.firebase.Firebase
import java.lang.ref.WeakReference

class LoginViewModel: ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val email = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun signUp() {

        val email = email.value
        val name = name.value
        val password = password.value

        if (email == null || name == null || password == null) {
            // Show Error Message from Main
            return
        }

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    create(User(email, name))
                } else {
                    Log.w(TAG, "signUp(user: User, password: String)", task.exception)
                    _errorMessage.value = task.exception?.localizedMessage
                }
            }
    }

    private fun create(user: User) {
        val id = Firebase.auth.currentUser?.uid
        val email = user.user_email
        val name = user.user_name
        if (user.user_email == null || user.user_name == null || id == null) {
            // Show Error Message from Main
            return
        }

        Firebase.firebase.collection(Firebase.userCollection)
            .document(id)
            .set(User(email, name, id))
            .addOnSuccessListener {
                Log.d(TAG, "create(user: User) : User Successfully Added")
                _loginSuccess.value = true
            }
            .addOnFailureListener {
                Log.w(TAG, "create(user: User) : ", it)
                _errorMessage.value = it.localizedMessage
            }
    }


    fun navigationComplete() {
        _loginSuccess.value = null
    }

}