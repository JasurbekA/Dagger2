package com.example.dagger2.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger2.data.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _userLoginState = MutableLiveData<UserAttemptLoginState>()
    val userAttemptLoginState: LiveData<UserAttemptLoginState>
        get() = _userLoginState

    fun authUser(userID: Int) {
        val disposable = authRepository.authUser(userID)
            .toObservable()
            .onErrorReturn { User() } // Return empty User Object to indicate fail
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _userLoginState.value = if (it.name.isEmpty())
                        UserAttemptLoginState.OnFailAuth
                    else UserAttemptLoginState.OnSuccess(it)
                    _userLoginState.value = UserAttemptLoginState.OnIdle
                },
                {
                    _userLoginState.value = UserAttemptLoginState.OnError(it)
                    _userLoginState.value = UserAttemptLoginState.OnIdle
                }
            )

        compositeDisposable.add(disposable)
    }

    sealed class UserAttemptLoginState {
        object OnLoading : UserAttemptLoginState()
        data class OnSuccess(val user: User) : UserAttemptLoginState()
        data class OnError(val err: Throwable) : UserAttemptLoginState()
        object OnIdle : UserAttemptLoginState()
        object OnFailAuth : UserAttemptLoginState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
