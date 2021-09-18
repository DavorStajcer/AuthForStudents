package di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import logIn.LogInFragment
import logIn.LogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repos.AuthRepo
import repos.IAuthRepo
import signUp.SingUpFragment
import signUp.SingUpViewModel


val appModule = module {

    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<IAuthRepo> { AuthRepo(get()) }

    factory { SingUpFragment() }
    viewModel { SingUpViewModel() }
    factory { LogInFragment() }
    viewModel { LogInViewModel(get()) }
}