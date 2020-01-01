package students.jimmy.firebasepractice.addpostfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import students.jimmy.firebasepractice.CurrentUser
import students.jimmy.firebasepractice.TAG
import students.jimmy.firebasepractice.dataclass.Post
import students.jimmy.firebasepractice.firebase.Firebase
import java.text.SimpleDateFormat
import java.util.*

enum class Status {
    Ready, Loading, Error
}

class AddPostViewModel : ViewModel() {
    val content = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    private val _tag = MutableLiveData<String>()
    val tag: LiveData<String>
        get() = _tag

    private val _posted = MutableLiveData<Boolean>()
    val posted: LiveData<Boolean>
        get() = _posted

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    fun addPost() {

        if (content.value == null ||
            content.value == "" ||
            title.value == null ||
            title.value == "" || _tag.value == null) {
            return
        }

        val format = SimpleDateFormat("yyyy/MM/dd HH:mm")

        val post = Post(
            article_title = title.value,
            article_content = content.value,
            article_tag = tag.value,
            author = CurrentUser.name,
            create_time = format.format(Date()),
            user_id = Firebase.auth.currentUser?.uid)

        _status.value = Status.Loading

        Firebase.firebase.collection(Firebase.articleCollection)
            .document()
            .set(post)
            .addOnCompleteListener {task ->

                if(task.isSuccessful) {
                    content.value = null
                    _posted.value = true
                } else {
                    Log.w(TAG, "addPost FAILED : ${task.exception?.localizedMessage}")
                }
                _status.value = Status.Ready
            }

    }

    fun setTag(tag: String) {
        _tag.value = tag
    }

    fun didShowSuccessLog() {
        _posted.value = null
        _status.value = Status.Ready
    }
}