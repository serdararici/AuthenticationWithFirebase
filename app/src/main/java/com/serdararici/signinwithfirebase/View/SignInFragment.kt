package com.serdararici.signinwithfirebase.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.serdararici.signinwithfirebase.R
import com.serdararici.signinwithfirebase.ViewModel.AuthViewModel
import com.serdararici.signinwithfirebase.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding?=null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)

        binding.signIntoSignUpText.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener{
            val email=binding.editEmailSignIn.text.toString()
            val password=binding.editPasswordSignIn.text.toString()

            viewModel.signInViewModel(email,password){ success, message ->

                if (success) {
                    // Giriş başarılı, ek işlemleri yapabilirsiniz.
                    val user = viewModel.currentUserViewModel()
                    // User nesnesini kullanabilir veya UI'yi güncelleyebilirsiniz.
                    navController.navigate(R.id.action_signInFragment_to_mainFragment)
                } else {
                    // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                    Toast.makeText(requireContext(), "Giriş başarısız: $message", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

}