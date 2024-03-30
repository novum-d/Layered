package io.novumd.core.model.user

data class UserEntity(
  val id: String,
  val name: String,
  val password: String,
)

fun User.asEntity() =
  UserEntity(
    id = id.value,
    name = name.value,
    password = password.value,
  )
