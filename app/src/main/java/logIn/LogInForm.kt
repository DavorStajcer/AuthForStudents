package logIn

import util.AuthFormInputValidator

class LogInForm (
    val email : String?,
    val password : String?,
) {
    var emailError : String? = null
    var passwordError : String? = null

    init {
        this.emailError = AuthFormInputValidator.mapToEmailError(email)
        this.passwordError = AuthFormInputValidator.mapToPasswordError(password)

    }

    constructor() : this(null,null)

    public fun isValid() =
        passwordError == null &&
        emailError == null &&
        email != null &&
        password != null


    public fun copyWith(email : String?,password : String?) : LogInForm =
        LogInForm(email ?: this.email, password ?: this.password)


}

