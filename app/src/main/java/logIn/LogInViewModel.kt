package logIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import repos.AuthRepo
import repos.IAuthRepo
import util.AuthFormInputValidator

class LogInViewModel(
    private val authRepo: IAuthRepo
) : ViewModel() {

    private val _logInForm = MutableLiveData<LogInForm>(LogInForm())
    val logInForm : LiveData<LogInForm>
        get() = this._logInForm

    private val _isFromValid = MutableLiveData<Boolean>(false)
    val isFormValid : LiveData<Boolean>
        get() = _isFromValid

    private val _navDirections = MutableLiveData<NavDirections>()
    val navDirections : LiveData<NavDirections>
        get() = _navDirections

    private val _screenState = MutableLiveData<ScreenState>(LoadedState())
    val screenState : LiveData<ScreenState>
        get() = _screenState




     fun onEmailChanged(newEmail : String?) {
        if(this._logInForm.value == null)
                return
        this._logInForm.value = this._logInForm.value!!.copyWith(newEmail,null)
        this._isFromValid.value = this._logInForm.value!!.isValid()
    }

    fun onPasswordChanged(newPassword : String?) {
        if(this._logInForm.value == null)
            return
        this._logInForm.value = this._logInForm.value!!.copyWith(null,newPassword)
        this._isFromValid.value = this._logInForm.value!!.isValid()
    }

    fun changeAuthMode(){
        this._navDirections.value = LogInFragmentDirections.openSingUp()
    }

    fun authenticate(){
        this._screenState.value = LoadingState()
        this.authRepo.signUp(email = _logInForm.value!!.email!!,password = _logInForm.value!!.email!!){ authResult ->
            if(authResult.isSuccessful)
                Log.v("auth","Authenticated !")
            else
                this._screenState.value = Failure("Couldnt authenticate")
        }
    }



}