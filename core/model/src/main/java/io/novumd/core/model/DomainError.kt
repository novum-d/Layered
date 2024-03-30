package io.novumd.core.model


fun example() {
  val err: UserRegistrationError = DomainError.UserExists
  when (err) {
    DomainError.UserExists -> {}
    DomainError.DatabaseError -> {}
    DomainError.UnexpectedError -> {}
  }
}

/** User */

// Register
sealed interface UserRegistrationError
sealed interface UserRegisterUsecaseError : UserRegistrationError

// Update
sealed interface UserUpdateUsecaseError


/** DomainError */
sealed interface DomainError {

  data object PasswordNotMatched : DomainError,
    UserUpdateUsecaseError

  data object UserNotFound : DomainError,
    UserUpdateUsecaseError

  data object UserExists : DomainError,
    UserRegistrationError

  data object UnexpectedError : DomainError,
    UserRegisterUsecaseError

  data object DatabaseError : DomainError,
    UserRegistrationError, UserUpdateUsecaseError

  data object NetworkError : DomainError
}