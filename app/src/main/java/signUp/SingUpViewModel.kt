package signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import logIn.LogInForm
import logIn.LogInFragmentDirections

class SingUpViewModel : ViewModel() {

    private val _signUpForm = MutableLiveData<SingUpForm>(SingUpForm())
    val signUpForm : LiveData<SingUpForm>
        get() = this._signUpForm

    private val _isFromValid = MutableLiveData<Boolean>(false)
    val isFormValid : LiveData<Boolean>
        get() = _isFromValid

    private val _navDirections = MutableLiveData<NavDirections>()
    val navDirections : LiveData<NavDirections>
        get() = _navDirections


    public fun onEmailChanged(newEmail : String?) {
        if(this._signUpForm.value == null)
            return
        this._signUpForm.value = this._signUpForm.value!!.copyWith(newEmail,null,null)
        this._isFromValid.value = this._signUpForm.value!!.isValid()
    }

    public fun onPasswordChanged(newPassword : String?) {
        if(this._signUpForm.value == null)
            return
        this._signUpForm.value = this._signUpForm.value!!.copyWith(null,newPassword,null)
        this._isFromValid.value = this._signUpForm.value!!.isValid()
    }

    public fun onConfirmPasswordChanged(newPassword : String?) {
        if(this._signUpForm.value == null)
            return
        this._signUpForm.value = this._signUpForm.value!!.copyWith(null,null,newPassword)
        this._isFromValid.value = this._signUpForm.value!!.isValid()
    }

    fun changeAuthMode(){
        this._navDirections.value = SingUpFragmentDirections.openLogIn()
    }

}