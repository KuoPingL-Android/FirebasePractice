package students.jimmy.firebasepractice.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import students.jimmy.firebasepractice.CurrentUser
import students.jimmy.firebasepractice.FirebasePracticeApplication
import students.jimmy.firebasepractice.R
import students.jimmy.firebasepractice.TAG
import students.jimmy.firebasepractice.dataclass.PostKeys
import students.jimmy.firebasepractice.dataclass.User
import students.jimmy.firebasepractice.dataclass.UserKeys

object Firebase {
    val firebase = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val userCollection: String = FirebasePracticeApplication
        .instance.applicationContext.getString(R.string.fb_user_collection)
    val articleCollection: String = FirebasePracticeApplication
        .instance.applicationContext.getString(R.string.fb_post_collection)


    // If it is current user, then simply direct into the view
    val isReturnUser: Boolean
        get() {

            val user = auth.currentUser

            user?.let {
                getUserInfoById(it.uid)
            }

            return auth.currentUser != null
        }

    val userRef = firebase.collection(userCollection)

    val articleRef = firebase.collection(articleCollection)

    fun query(name: String): Query {
        return userRef.whereEqualTo(UserKeys.Name.string, name)
    }

    fun queryAllPosts(): Query {
        return articleRef.orderBy(PostKeys.Date.string, Query.Direction.ASCENDING)
    }

    fun getUserInfoById(id: String) {
        userRef.whereEqualTo(UserKeys.ID.string, id)
            .get().addOnCompleteListener {task ->
            if (task.isSuccessful) {
                task.result?.toObjects(User::class.java)?.first()?.let {user ->
                    CurrentUser.setUser(user)
                }
            }
        }
    }

}