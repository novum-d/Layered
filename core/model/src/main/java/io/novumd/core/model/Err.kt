package io.novumd.core.model


fun example() {
  val err: UserRegisterDataError = Err.UserExists
  when (err) {
    Err.UserExists -> {}
    Err.DatabaseError -> {}
    Err.UnexpectedError -> {}
    Err.NetworkError -> {}
  }
}

// Domain Layer error type -> 〇〇DomainError
// Data Layer error type-> 〇〇DataError

/** User */
sealed interface UserRegisterDomainError : UserRegisterDataError
sealed interface UserRegisterDataError

sealed interface UserUpdateDomainError : UserUpdateDataError
sealed interface UserUpdateDataError


/** Error Type */
sealed interface Err {

  /** Domain Layer */
  data object PasswordNotMatched : Err,
    UserUpdateDomainError

  data object UserNotFound : Err,
    UserUpdateDomainError

  data object UserExists : Err,
    UserRegisterDomainError


  /** Data Layer */
  data object UnexpectedError : Err,
    UserRegisterDataError, UserUpdateDataError

  data object DatabaseError : Err,
    UserRegisterDataError, UserUpdateDataError

  data object NetworkError : Err,
    UserRegisterDataError, UserUpdateDataError
}
