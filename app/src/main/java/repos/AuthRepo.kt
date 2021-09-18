package repos

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import common.Failure
import common.RepoResponse
import common.Success
import java.util.concurrent.Future

class AuthRepo (
        private val firebaseAuth: FirebaseAuth
) : IAuthRepo {
        override  fun  logIn(email: String, password: String,onCompleteListener: OnCompleteListener<AuthResult>) : Unit {
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(onCompleteListener)
        }
        override fun signUp(email: String, password: String,onCompleteListener: OnCompleteListener<AuthResult>): Unit {
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(onCompleteListener)
        }

        override fun logOut() {
                TODO("Not yet implemented")
        }

}
