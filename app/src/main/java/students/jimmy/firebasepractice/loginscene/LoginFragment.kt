package students.jimmy.firebasepractice.loginscene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import students.jimmy.firebasepractice.MainActivity
import students.jimmy.firebasepractice.MainViewModel
import students.jimmy.firebasepractice.databinding.FragmentLoginBinding
import students.jimmy.firebasepractice.firebase.Firebase
import students.jimmy.firebasepractice.viewmodelfactory.LoginViewModelFactory
import java.lang.ref.WeakReference

class LoginFragment : Fragment() {



    private lateinit var binding: FragmentLoginBinding
    private val viewModel by lazy {
        LoginViewModelFactory().create(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewmodel = viewModel

        viewModel.loginSuccess.observe(this, Observer {
            (activity as? MainActivity)?.navigateToUserPages()
        })

        if (Firebase.isReturnUser) {
            (activity as? MainActivity)?.navigateToUserPages()
        }

        return binding.root
    }
}