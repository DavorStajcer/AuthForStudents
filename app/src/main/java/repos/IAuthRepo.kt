package repos

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import common.RepoResponse
import java.util.concurrent.Future

interface IAuthRepo {
    fun logIn(email : String, password : String,onCompleteListener: OnCompleteListener<AuthResult>) : Unit
    fun signUp(email : String, password: String,onCompleteListener: OnCompleteListener<AuthResult>) : Unit
    fun logOut()
}