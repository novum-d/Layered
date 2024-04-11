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

context(Raise<NonEmptyList<Err.Domain.UserInvalidError>>)
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
    name: String,
    email: String,
  ) {
    copy {
      UserUpdateCommand.name set name
      UserUpdateCommand.email set email
    }
  }

  companion object
}

context(Raise<NonEmptyList<Err.Domain.UserInvalidError>>)
fun UserUpdateCommand.validate() {
  zipOrAccumulate(
    { name?.let(::UserName)?.validate() },
    { email?.let(::UserEmail)?.validate() },
    { password.let(::UserPassword).validate() },
  ) { _, _, _ ->
  }
}
