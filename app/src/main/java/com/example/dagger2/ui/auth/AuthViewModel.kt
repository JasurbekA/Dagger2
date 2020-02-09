package com.example.dagger2.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger2.SessionManager
import com.example.dagger2.data.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val userAttemptLoginState: LiveData<UserAttemptLoginState>
        get() = sessionManager.userState

    fun authUser(userID: Int) {
        val disposable = authRepository.authUser(userID)
            .toObservable()
            .onErrorReturn { User() } // Return empty User Object to indicate fail
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    sessionManager.authUserWithID(
                        if (it.name.isEmpty()) UserAttemptLoginState.OnUserNotAuthenticated
                        else UserAttemptLoginState.OnSuccess(it)
                    )
                },
                { sessionManager.authUserWithID(UserAttemptLoginState.OnError(it)) }
            )

        compositeDisposable.add(disposable)
    }

    sealed class UserAttemptLoginState {
        object OnLoading : UserAttemptLoginState()
        data class OnSuccess(val user: User) : UserAttemptLoginState()
        data class OnError(val err: Throwable) : UserAttemptLoginState()
        object OnIdle : UserAttemptLoginState()
        object OnUserNotAuthenticated : UserAttemptLoginState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
