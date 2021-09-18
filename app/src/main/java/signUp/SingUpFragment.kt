package signUp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookkeep.databinding.SingUpLayoutBinding

class SingUpFragment : Fragment() {

    private lateinit var binding : SingUpLayoutBinding
    private val viewModel by viewModels<SingUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingUpLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToFormChanges()
        subscribeToAuthModeChange()
        observeUiChanges()
    }



    private fun observeUiChanges(){
        viewModel.apply {
            signUpForm.observe(viewLifecycleOwner,{ singUpForm ->
                Log.v("auth","Log in form changed, emailError -> ${singUpForm?.emailError}, passError -> ${singUpForm?.passwordError},confError -> ${singUpForm?.confirmPasswordError}")
                binding.apply {
                    emailTextInputLayout.error = singUpForm.emailError
                    passwordTextInputLayout.error = singUpForm.passwordError
                    confirmPasswordTextInputLayout.error = singUpForm.confirmPasswordError
                }
            })
            isFormValid.observe(viewLifecycleOwner,{ isValid ->
                Log.v("auth","Is form valid-> $isValid")
                binding.isFormValid = isValid
            })
            navDirections.observe(viewLifecycleOwner, { directions ->
                findNavController().navigate(directions)
            })
        }
    }

    private fun subscribeToAuthModeChange(){
        binding.txtLogInInstead.setOnClickListener { _ ->
            viewModel.changeAuthMode()
        }
    }

    private fun subscribeToFormChanges(){
        subscribeToEmailChanges()
        subscribeToPasswordChanges()
        subscribeToConfirmPasswordChanges()
    }

    private fun subscribeToEmailChanges(){
        val emailTextInputLayout = this.binding.emailTextInputLayout
        emailTextInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newEmail = s?.toString()?.trim()
                this@SingUpFragment.viewModel.onEmailChanged(newEmail)
            }
            override fun afterTextChanged(s: Editable?) {
                //Do nothing
            }

        })
    }
    private fun subscribeToPasswordChanges(){
        val passwordTextInputLayout = this.binding.passwordTextInputLayout
        passwordTextInputLayout.editText?.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newPassword = s?.toString()?.trim()
                this@SingUpFragment.viewModel.onPasswordChanged(newPassword)
            }

            override fun afterTextChanged(s: Editable?) {
                //Do nothing
            }
        })
    }

    private fun subscribeToConfirmPasswordChanges(){
        val passwordTextInputLayout = this.binding.confirmPasswordTextInputLayout
        passwordTextInputLayout.editText?.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newPassword = s?.toString()?.trim()
                this@SingUpFragment.viewModel.onConfirmPasswordChanged(newPassword)
            }

            override fun afterTextChanged(s: Editable?) {
                //Do nothing
            }
        })
    }

}