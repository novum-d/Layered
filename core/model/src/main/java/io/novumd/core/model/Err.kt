package io.novumd.core.model

import arrow.core.NonEmptyList


fun example() {
  val err: UserRegisterUsecaseError = Err.Data.NetworkError
  when (err) {
    Err.Domain.UserIdAlreadyExists -> TODO()
    Err.Domain.UserEmailAlreadyExists -> TODO()
    Err.Data.NetworkError -> TODO()
    Err.Data.UnexpectedError -> TODO()
    Err.Data.DatabaseError -> TODO()
    is UserInvalid -> err.nel.forEach {
      when (it) {
        Err.Domain.UserInvalidError.UserEmailInvalid -> TODO()
        Err.Domain.UserInvalidError.UserIdInvalid -> TODO()
        Err.Domain.UserInvalidError.UserNameInvalid -> TODO()
        Err.Domain.UserInvalidError.UserPasswordInvalid -> TODO()
      }
    }
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

// UserInvalid
data class UserInvalid(
  val nel: NonEmptyList<Err.Domain.UserInvalidError>,
) : UserRegisterUsecaseError, UserUpdateUsecaseError

/** Data Layer error type */
// example: 〇〇DataError
sealed interface UserRegisterDataError : UserRegisterUsecaseError
sealed interface UserCreateIdDataError : UserRegisterUsecaseError
sealed interface UserSaveDataError : UserUpdateUsecaseError
sealed interface UserFetchDataError : UserUpdateUsecaseError,
  UserIdExistsDomainServiceError, UserEmailExistsDomainServiceError


/** Error Type */
sealed interface Err {

  /** Domain Layer */
  sealed interface Domain : Err {
    sealed interface UserInvalidError : Domain, UserUpdateUsecaseError {
      data object UserIdInvalid : UserInvalidError
      data object UserNameInvalid : UserInvalidError
      data object UserPasswordInvalid : UserInvalidError
      data object UserEmailInvalid : UserInvalidError
    }

    data object PasswordNotMatched : Domain, UserUpdateUsecaseError
    data object UserNotFound : Domain, UserUpdateUsecaseError
    data object UserIdAlreadyExists : Domain, UserIdExistsDomainServiceError
    data object UserEmailAlreadyExists : Domain, UserEmailExistsDomainServiceError
  }

  /** Data Layer */
  sealed interface Data : Err {
    data object UnexpectedError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFetchDataError

    data object DatabaseError : Data,
      UserRegisterDataError, UserSaveDataError

    data object NetworkError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFetchDataError
  }
}
