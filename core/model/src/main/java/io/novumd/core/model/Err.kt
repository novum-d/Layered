package io.novumd.core.model

import arrow.core.NonEmptyList


fun example() {
  val err: UserRegisterUseCaseError = Err.Data.NetworkError
  when (err) {
    Err.Domain.UserIdAlreadyExists -> TODO()
    Err.Domain.UserEmailAlreadyExists -> TODO()
    Err.Data.NetworkError -> TODO()
    Err.Data.UnexpectedError -> TODO()
    Err.Data.DatabaseError -> TODO()
    is UserInvalid -> err.nel.forEach {
      when (it) {
        Err.Domain.UserInvalidError.UserEmailInvalid -> TODO()
        Err.Domain.UserInvalidError.UserNameInvalid -> TODO()
        Err.Domain.UserInvalidError.UserPasswordInvalid -> TODO()
      }
    }
  }
}

/** Domain Layer error type */

/* UseCase */
// example: 〇〇UseCaseError
sealed interface UserRegisterUseCaseError
sealed interface UserUpdateUseCaseError

/* DomainService */
// example: 〇〇DomainServiceError
sealed interface UserIdExistsDomainServiceError : UserRegisterUseCaseError
sealed interface UserEmailExistsDomainServiceError : UserRegisterUseCaseError, UserUpdateUseCaseError

// UserInvalid
data class UserInvalid(
  val nel: NonEmptyList<Err.Domain.UserInvalidError>,
) : UserRegisterUseCaseError, UserUpdateUseCaseError

/** Data Layer error type */
// example: 〇〇DataError
sealed interface UserRegisterDataError : UserRegisterUseCaseError
sealed interface UserCreateIdDataError : UserRegisterUseCaseError
sealed interface UserSaveDataError : UserUpdateUseCaseError
sealed interface UserFindDataError : UserUpdateUseCaseError,
  UserIdExistsDomainServiceError, UserEmailExistsDomainServiceError


/** Error Type */
sealed interface Err {

  /** Domain Layer */
  sealed interface Domain : Err {
    sealed interface UserInvalidError : Domain, UserUpdateUseCaseError {
      data object UserNameInvalid : UserInvalidError
      data object UserPasswordInvalid : UserInvalidError
      data object UserEmailInvalid : UserInvalidError
    }

    data object PasswordNotMatched : Domain, UserUpdateUseCaseError
    data object UserNotFound : Domain, UserUpdateUseCaseError
    data object UserIdAlreadyExists : Domain, UserIdExistsDomainServiceError
    data object UserEmailAlreadyExists : Domain, UserEmailExistsDomainServiceError
  }

  /** Data Layer */
  sealed interface Data : Err {
    data object UnexpectedError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError

    data object DatabaseError : Data,
      UserRegisterDataError, UserSaveDataError, UserFindDataError

    data object NetworkError : Data,
      UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError
  }
}
