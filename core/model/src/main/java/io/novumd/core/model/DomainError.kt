package io.novumd.core.model


fun example() {
  val err: UserRegisterUseCaseError = DomainError.UserExists
  when (err) {
    DomainError.UserExists -> {}
    DomainError.DatabaseError -> {}
  }
}

sealed interface UserRegisterUseCaseError

sealed interface DomainError {

  data object UserExists : DomainError, UserRegisterUseCaseError

  data object UnexpectedError : DomainError
  data object DatabaseError : DomainError, UserRegisterUseCaseError
  data object NetworkError : DomainError
}