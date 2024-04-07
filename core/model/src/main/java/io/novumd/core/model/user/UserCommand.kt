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

@optics
data class UserUpdateCommand(
  val id: String,
  val name: String?,
  val email: String?,
  val password: String,
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
    { id.let(::UserId).validate() },
    { name?.let(::UserName)?.validate() },
    { email?.let(::UserEmail)?.validate() },
    { password.let(::UserPassword).validate() },
  ) { _, _, _, _ -> }
}
