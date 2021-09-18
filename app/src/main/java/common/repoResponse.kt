package common

import com.google.firebase.database.core.Repo

interface RepoResponse<T>

class Success : RepoResponse<Unit>

class Failure : RepoResponse<Unit>

class FetchedData<T> : RepoResponse<T>



