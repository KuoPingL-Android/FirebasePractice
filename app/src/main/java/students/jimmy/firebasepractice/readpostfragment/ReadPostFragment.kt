package students.jimmy.firebasepractice.readpostfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import students.jimmy.firebasepractice.databinding.FragmentReadpostBinding
import students.jimmy.firebasepractice.viewmodelfactory.GeneralViewModelFactory

class ReadPostFragment : Fragment() {

    private val viewModel by lazy {
        GeneralViewModelFactory().create(ReadPostViewModel::class.java)
    }
    private lateinit var binding: FragmentReadpostBinding
    private val adpater = ReadPostRecyclerViewAdpater()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReadpostBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.recyclerviewReadpost.adapter = adpater

        binding.viewmodel = viewModel

        viewModel.fetchAllPost()

        return binding.root
    }
}