package students.jimmy.firebasepractice.readpostfragment

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import students.jimmy.firebasepractice.TAG
import students.jimmy.firebasepractice.dataclass.Post
import students.jimmy.firebasepractice.firebase.Firebase
import java.util.*

class ReadPostViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private var registration: ListenerRegistration? = null

    fun fetchAllPost() {

        registration?.remove()

        Firebase.queryAllPosts().get().addOnSuccessListener {snapshot ->
            _posts.value = snapshot.toObjects(Post::class.java)
            Log.w(TAG, "fetchAllPost : Success, ${posts.value}")
        }.addOnFailureListener {
            Log.w(TAG, "fetchAllPost : Failed, ${it.localizedMessage}")
        }

        registration = Firebase.articleRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                Log.d(TAG, "Current data: ${snapshot.toObjects(Post::class.java)}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }
}