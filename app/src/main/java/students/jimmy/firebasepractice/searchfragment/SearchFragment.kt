package students.jimmy.firebasepractice.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import students.jimmy.firebasepractice.FirebasePracticeApplication
import students.jimmy.firebasepractice.bindingadapter.bind
import students.jimmy.firebasepractice.databinding.FragmentSearchBinding
import students.jimmy.firebasepractice.viewmodelfactory.GeneralViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by lazy {
        GeneralViewModelFactory().create(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewmodel = viewModel

        viewModel.errorMessage.observe(this, Observer {
            it?.let { message ->
                Toast.makeText(FirebasePracticeApplication.instance.applicationContext
                    , message, Toast.LENGTH_SHORT).show()
                viewModel.resetMessage()
            }
        })

        return binding.root
    }
}