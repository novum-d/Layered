package io.novumd.core.model.user

import arrow.core.NonEmptyList
import arrow.core.raise.Raise
import arrow.core.raise.zipOrAccumulate
import arrow.optics.copy
import arrow.optics.optics
import io.novumd.core.model.Err


data class UserRegisterCommand(
  val name: String,
  val email: String,
  val password: String,
)

context(Raise<NonEmptyList<Err.Domain.UserInvalid>>)
fun UserRegisterCommand.validate() {
  zipOrAccumulate(
    { name.let(::UserName).validate() },
    { email.let(::UserEmail).validate() },
    { password.let(::UserPassword).validate() },
  ) { _, _, _ ->
  }
}


@optics
data class UserUpdateCommand(
  val password: String,
  val name: String? = null,
  val email: String? = null,
) {

  operator fun invoke(
    name: String?,
    email: String?,
  ) {
    copy {
      UserUpdateCommand.nullableName set name
      UserUpdateCommand.nullableEmail set email
    }
  }

  companion object
}

context(Raise<NonEmptyList<Err.Domain.UserInvalid>>)
fun UserUpdateCommand.validate() {
  zipOrAccumulate(
    { password.let(::UserPassword).validate() },
    { name?.let(::UserName)?.validate() },
    { email?.let(::UserEmail)?.validate() },
  ) { _, _, _ ->
  }
}
