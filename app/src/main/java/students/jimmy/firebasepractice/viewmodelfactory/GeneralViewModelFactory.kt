package students.jimmy.firebasepractice.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import students.jimmy.firebasepractice.MainViewModel
import students.jimmy.firebasepractice.addpostfragment.AddPostViewModel
import students.jimmy.firebasepractice.loginscene.LoginViewModel
import students.jimmy.firebasepractice.readpostfragment.ReadPostViewModel
import students.jimmy.firebasepractice.searchfragment.SearchFragment
import students.jimmy.firebasepractice.searchfragment.SearchViewModel
import java.lang.IllegalArgumentException
import java.lang.ref.WeakReference

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when  {
                isAssignableFrom(MainViewModel::class.java) -> {
                    MainViewModel()
                }
                else -> {
                    throw IllegalArgumentException("MainViewModelFactory : Unknown Class")
                }
            }
        } as T
    }
}


class LoginViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->  {
                    LoginViewModel()
                }
                else -> {
                    throw IllegalArgumentException("LoginViewModelFactory : Unknown class")
                }
            }

        } as T
    }
}

class GeneralViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(ReadPostViewModel::class.java) -> {
                    ReadPostViewModel()
                }

                isAssignableFrom(AddPostViewModel::class.java) -> {
                    AddPostViewModel()
                }

                isAssignableFrom(SearchViewModel::class.java) -> {
                    SearchViewModel()
                }

                else -> {
                    throw IllegalArgumentException("GeneralViewModelFactory : Unknown class")
                }
            }
        } as T
    }
}