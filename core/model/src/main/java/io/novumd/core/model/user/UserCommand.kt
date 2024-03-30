package io.novumd.core.model.user

data class UserRegisterCommand(
  val name: String,
  val password: String,
)

data class UserUpdateCommand(
  val id: String,
  val name: String,
  val password: String,
)
