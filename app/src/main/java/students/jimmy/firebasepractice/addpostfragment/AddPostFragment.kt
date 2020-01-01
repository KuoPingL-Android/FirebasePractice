package students.jimmy.firebasepractice.addpostfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.analytics.FirebaseAnalytics
import students.jimmy.firebasepractice.FirebasePracticeApplication
import students.jimmy.firebasepractice.R
import students.jimmy.firebasepractice.bindingadapter.bind
import students.jimmy.firebasepractice.databinding.FragmentWritepostBinding
import students.jimmy.firebasepractice.viewmodelfactory.GeneralViewModelFactory

class AddPostFragment : Fragment() {
    private lateinit var binding: FragmentWritepostBinding
    private val viewModel by lazy {
        GeneralViewModelFactory().create(AddPostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritepostBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val firebaseContext = FirebasePracticeApplication.instance.applicationContext

        val list = listOf(firebaseContext.getString(R.string.tags_joke),
            firebaseContext.getString(R.string.tags_beauty),
            firebaseContext.getString(R.string.tags_gossiping),
            firebaseContext.getString(R.string.tags_schoollife) )

        val adapter = ArrayAdapter(FirebasePracticeApplication.instance.applicationContext,
            android.R.layout.simple_spinner_dropdown_item, list)

        binding.spinnerWritepost.adapter = adapter

        binding.spinnerWritepost.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setTag(list[position])
            }
        }

        viewModel.posted.observe(this, Observer {
            it?.let {
                Toast.makeText(FirebasePracticeApplication.instance.applicationContext,
                    "POSTED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                viewModel.didShowSuccessLog()
            }
        })

        return binding.root
    }
}