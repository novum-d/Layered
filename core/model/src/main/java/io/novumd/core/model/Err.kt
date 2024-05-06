package io.novumd.core.model

import arrow.core.NonEmptyList


fun example() {
    when (val err: UserRegisterUseCaseError = Err.Data.NetworkError) {
        // Domain Error
        Err.Domain.UserIdAlreadyExists -> TODO()
        Err.Domain.UserEmailAlreadyExists -> TODO()
        is UserInvalidError -> err.nel.forEach {
            when (it) {
                Err.Domain.UserInvalid.Id -> TODO()
                Err.Domain.UserInvalid.Email -> TODO()
                Err.Domain.UserInvalid.Name -> TODO()
                Err.Domain.UserInvalid.Password -> TODO()
            }
        }

        // Data Error
        Err.Data.NetworkError -> TODO()
        Err.Data.UnexpectedError -> TODO()
        Err.Data.DatabaseError -> TODO()
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
sealed interface AppTamperedWithDomainServiceError : UserUpdateUseCaseError

/* ValidationError */
data class UserInvalidError(
    val nel: NonEmptyList<Err.Domain.UserInvalid>,
) : UserRegisterUseCaseError, UserUpdateUseCaseError

/** Data Layer error type */
// example: 〇〇DataError
sealed interface UserRegisterDataError : UserRegisterUseCaseError
sealed interface UserCreateIdDataError : UserRegisterUseCaseError
sealed interface UserSaveDataError : UserUpdateUseCaseError
sealed interface UserFindDataError : UserUpdateUseCaseError,
    UserIdExistsDomainServiceError, UserEmailExistsDomainServiceError

sealed interface AppTamperedWithDataError : AppTamperedWithDomainServiceError


/** Handled Error Type */
sealed interface Err {

    sealed interface Domain : Err {
        sealed interface UserInvalid : Domain, UserUpdateUseCaseError {
            data object Id : UserInvalid
            data object Name : UserInvalid
            data object Password : UserInvalid
            data object Email : UserInvalid
        }

        data object PasswordNotMatched : Domain, UserUpdateUseCaseError
        data object UserNotFound : Domain, UserUpdateUseCaseError
        data object UserIdAlreadyExists : Domain, UserIdExistsDomainServiceError
        data object UserEmailAlreadyExists : Domain, UserEmailExistsDomainServiceError
    }

    sealed interface Data : Err {
        data object UnexpectedError : Data,
            UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError,
            AppTamperedWithDataError

        data object DatabaseError : Data,
            UserRegisterDataError, UserSaveDataError, UserFindDataError,
            AppTamperedWithDataError

        data object NetworkError : Data,
            UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError,
            AppTamperedWithDataError
    }
}
