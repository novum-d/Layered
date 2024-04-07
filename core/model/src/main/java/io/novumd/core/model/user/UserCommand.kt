package io.novumd.core.model.user

import arrow.core.NonEmptyList
import arrow.core.raise.Raise
import arrow.core.raise.zipOrAccumulate
import arrow.optics.copy
import arrow.optics.optics
import io.novumd.core.model.Err

interface UserCommand

data class UserRegisterCommand(
  val name: String,
  val email: String,
  val password: String,
) : UserCommand

context(Raise<NonEmptyList<Err.Domain.UserInvalidError>>)
fun UserUpdateCommand.validate() {
  zipOrAccumulate(
    { id.let(::UserId).validate() },
    { name?.let(::UserName)?.validate() },
    { email?.let(::UserEmail)?.validate() },
    { password.let(::UserPassword).validate() },
  ) { _, _, _, _ ->
  }
}

@optics
data class UserUpdateCommand(
  val id: String,
  val name: String?,
  val email: String?,
  val password: String,
) : UserCommand {

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
fun UserRegisterCommand.validate() {
  zipOrAccumulate(
    { name.let(::UserName).validate() },
    { email.let(::UserEmail).validate() },
    { password.let(::UserPassword).validate() },
  ) { _, _, _ ->
  }
}
