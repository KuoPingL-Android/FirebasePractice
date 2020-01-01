package students.jimmy.firebasepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import students.jimmy.firebasepractice.databinding.ActivityMainBinding
import students.jimmy.firebasepractice.firebase.Firebase
import students.jimmy.firebasepractice.viewmodelfactory.MainViewModelFactory
import java.lang.ref.WeakReference
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val TAG = "FIREBASE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun navigateToUserPages() {
        val navCon = findNavController(R.id.fragment_main)
        binding.bottomnavMain.setupWithNavController(navCon)
        binding.bottomnavMain.visibility = View.VISIBLE
        navCon.navigate(MainNavDirections.actionGlobalReadPostFragment())
    }
}

