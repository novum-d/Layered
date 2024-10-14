package io.novumd.core.model

import arrow.core.NonEmptyList


fun example() {
    when (val err: UserUpdateUseCaseError = DataErr.Network) {
        // Domain Error
        DomainErr.UserEmailAlreadyExists -> TODO()
        DomainErr.PasswordNotMatched -> TODO()
        DomainErr.UserNotFound -> TODO()
        is UserInputError -> err.nel.forEach {
            when (it) {
                DomainErr.UserInput.Email -> TODO()
                DomainErr.UserInput.Name -> TODO()
                DomainErr.UserInput.Password -> TODO()
            }
        }

        // Data Error
        DataErr.Network -> TODO()
        DataErr.Unexpected -> TODO()
        DataErr.Database -> TODO()
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
data class UserInputError(
    val nel: NonEmptyList<DomainErr.UserInput>,
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
sealed interface Err

sealed interface DomainErr : Err {
    sealed interface UserInput : DomainErr {
        data object Name : UserInput
        data object Password : UserInput
        data object Email : UserInput
    }

    data object PasswordNotMatched : DomainErr, UserUpdateUseCaseError
    data object UserNotFound : DomainErr, UserUpdateUseCaseError
    data object UserIdAlreadyExists : DomainErr, UserIdExistsDomainServiceError
    data object UserEmailAlreadyExists : DomainErr, UserEmailExistsDomainServiceError
}

sealed interface DataErr : Err {
    data object Unexpected : DataErr,
        UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError,
        AppTamperedWithDataError

    data object Database : DataErr,
        UserRegisterDataError, UserSaveDataError, UserFindDataError,
        AppTamperedWithDataError

    data object Network : DataErr,
        UserRegisterDataError, UserSaveDataError, UserCreateIdDataError, UserFindDataError,
        AppTamperedWithDataError
}

