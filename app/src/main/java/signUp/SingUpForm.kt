package signUp

import util.AuthFormInputValidator

class SingUpForm (
    val email : String?,
    val password : String?,
    val confirmPassword : String?
) {
    var emailError : String? = null
    var passwordError : String? = null
    var confirmPasswordError : String? = null

    init {
        this.emailError = AuthFormInputValidator.mapToEmailError(email)
        this.passwordError = AuthFormInputValidator.mapToPasswordError(password)
        this.confirmPasswordError = AuthFormInputValidator.mapToConfrimPasswordError(confirmPassword,password)
    }

    constructor() : this(null,null,null)

    public fun isValid() = passwordError == null &&
                emailError == null &&
                confirmPasswordError == null &&
                email != null &&
                password != null &&
                confirmPassword != null


    public fun copyWith(email : String?,password : String?,confirmPassword: String?) : SingUpForm =
        SingUpForm(email ?: this.email, password ?: this.password,confirmPassword ?: this.confirmPassword)


}