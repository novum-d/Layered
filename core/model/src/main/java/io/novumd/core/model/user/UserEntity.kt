package io.novumd.core.model.user

data class UserEntity(
  val id: String,
  val password: String,
  val name: String?,
  val email: String?,
)

fun User.asEntity() =
  UserEntity(
    id = id.value,
    password = password.value,
    name = name?.value,
    email = email?.value,
  )
