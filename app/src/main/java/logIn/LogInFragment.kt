package logIn

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bookkeep.databinding.LogInLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {

    private lateinit var binding : LogInLayoutBinding
    private  val viewModel : LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = LogInLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToFormChanges()
        subscribeToAuthModeChange()
        subscribeToAuthInit()
        obeserveViewModel()


    }

    private fun subscribeToAuthModeChange(){
        binding.txtSingUpInstead.setOnClickListener { _ ->
            viewModel.changeAuthMode()
        }
    }

    private fun obeserveViewModel(){
        viewModel.apply {
            logInForm.observe(viewLifecycleOwner,object : Observer<LogInForm>{
                override fun onChanged(logInForm: LogInForm?) {
                    Log.v("auth","Log in form changed, emailError -> ${logInForm?.emailError}, passError -> ${logInForm?.passwordError}")
                    if(logInForm == null)
                        return
                    binding.apply {
                        emailTextInputLayout.error = logInForm.emailError
                        passwordTextInputLayout.error = logInForm.passwordError
                    }
                }
            })
            isFormValid.observe(viewLifecycleOwner, { isValid ->
                Log.v("auth","Is form valid-> $isValid")
                binding.isFormValid = isValid
            })
            navDirections.observe(viewLifecycleOwner, { directions ->
                findNavController().navigate(directions)
            })
            screenState.observe(viewLifecycleOwner,{ screenState ->
                if(screenState is LoadingState)
                    binding.apply {
                        loaderVisibility = View.VISIBLE
                        loadedScreenVisibility = View.INVISIBLE
                    }
                else{
                    binding.apply {
                        loaderVisibility = View.INVISIBLE
                        loadedScreenVisibility = View.VISIBLE
                    }
                    if(screenState is Failure)
                        Toast.makeText(requireActivity().applicationContext,screenState.errorMsg,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun subscribeToAuthInit(){
        binding.btnLogIn.setOnClickListener { _ ->
            viewModel.authenticate()
        }
    }

    private fun subscribeToFormChanges(){
        this.subscribeToEmailChanges()
        this.subscribeToPasswordChanges()
    }

    private fun subscribeToEmailChanges(){
        val emailTextInputLayout = this.binding.emailTextInputLayout
        emailTextInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newEmail = s?.toString()?.trim()
                this@LogInFragment.viewModel.onEmailChanged(newEmail)
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
                this@LogInFragment.viewModel.onPasswordChanged(newPassword)
            }

            override fun afterTextChanged(s: Editable?) {
                //Do nothing
            }

        })
    }
}