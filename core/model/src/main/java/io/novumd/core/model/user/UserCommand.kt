package io.novumd.core.model.user

import arrow.optics.copy
import arrow.optics.optics

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
