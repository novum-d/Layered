package io.novumd.core.model


fun example() {
  val err: UserRegisterUsecaseError = Err.Data.NetworkError
  when (err) {
    Err.Data.NetworkError -> TODO()
    Err.Data.UnexpectedError -> TODO()
    Err.Domain.UserAlreadyExists -> TODO()
    Err.Data.DatabaseError -> TODO()
  }
}

/** Domain Layer error type */
/* Usecase */
// example: 〇〇UsecaseError
sealed interface UserRegisterUsecaseError
sealed interface UserUpdateUsecaseError

/* DomainService */
// example: 〇〇DomainServiceError
sealed interface UserIdExistsDomainServiceError : UserRegisterUsecaseError
sealed interface UserEmailExistsDomainServiceError : UserRegisterUsecaseError, UserUpdateUsecaseError

/** Data Layer error type */
// example: 〇〇DataError
sealed interface UserRegisterDataError : UserRegisterUsecaseError
sealed interface UserCreateIdDataError : UserRegisterUsecaseError
sealed interface UserSaveDataError : UserUpdateUsecaseError
sealed interface UserFindDataError : UserUpdateUsecaseError


/** Error Type */
sealed interface Err {

  /** Domain Layer */
  sealed interface Domain : Err {
    data object PasswordNotMatched : Domain,
      UserUpdateUsecaseError

    data object UserNotFound : Domain,
      UserUpdateUsecaseError

    data object UserAlreadyExists : Domain,
      UserRegisterUsecaseError
  }

  /** Data Layer */
  sealed interface Data : Err {
    data object UnexpectedError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError

    data object DatabaseError : Data,
      UserRegisterDataError, UserSaveDataError

    data object NetworkError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError
  }
}
