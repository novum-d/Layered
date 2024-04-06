package io.novumd.core.model


fun example() {
  val err: UserRegisterUsecaseError = Err.NetworkError
  when (err) {
    Err.UserExists -> {}
    Err.DatabaseError -> {}
    Err.UnexpectedError -> {}
    Err.NetworkError -> {}
  }
}

/** Domain Layer error type */
/* Usecase */
// example: 〇〇UsecaseError
sealed interface UserRegisterUsecaseError
sealed interface UserUpdateUsecaseError

/* DomainService */
// example: 〇〇DomainError
sealed interface UserExistsDomainError : UserRegisterUsecaseError

/** Data Layer error type */
// example: 〇〇DataError
sealed interface UserRegisterDataError : UserRegisterUsecaseError
sealed interface UserCreateIdDataError : UserRegisterUsecaseError
sealed interface UserSaveDataError : UserUpdateUsecaseError
sealed interface UserFindDataError : UserUpdateUsecaseError


/** Error Type */
sealed interface Err {

  /** Domain Layer */
  data object PasswordNotMatched : Err,
    UserUpdateUsecaseError

  data object UserNotFound : Err,
    UserUpdateUsecaseError

  data object UserExists : Err,
    UserRegisterUsecaseError


  /** Data Layer */
  data object UnexpectedError : Err,
    UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError

  data object DatabaseError : Err,
    UserRegisterDataError, UserSaveDataError

  data object NetworkError : Err,
    UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError
}
