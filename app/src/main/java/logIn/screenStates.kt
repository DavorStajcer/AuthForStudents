package logIn

interface ScreenState

class LoadingState : ScreenState

class LoadedState : ScreenState

class Failure(
    val errorMsg : String
) : ScreenState
