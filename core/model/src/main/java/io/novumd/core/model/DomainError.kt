package io.novumd.core.model


sealed interface DomainError {
  data object Unexpected : DomainError
  data object Network : DomainError
  data object RequiredPassword : DomainError
  data object WrongPassword : DomainError
}