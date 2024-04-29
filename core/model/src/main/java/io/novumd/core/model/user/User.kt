package io.novumd.core.model.user

import arrow.core.raise.Raise
import io.novumd.core.model.UserCreateIdDataError


interface User {
  val id: UserId
  val password: UserPassword
  val name: UserName?
  val email: UserEmail?

  companion object {
    context(Raise<UserCreateIdDataError>, UserFactoryCommand)
    fun create(
      name: String,
      email: String,
      password: String,
    ): User =
      UserData(
        id = createId(),
        password = password.let(::UserPassword),
        name = name.let(::UserName),
        email = email.let(::UserEmail),
      )
  }
}

interface UserFactoryCommand {
  context (Raise<UserCreateIdDataError>)
  fun createId(): UserId
}

private data class UserData(
  override val id: UserId,
  override val password: UserPassword,
  override val name: UserName? = null,
  override val email: UserEmail? = null,
) : User


fun UserUpdateCommand.asExternalModel(id: String): User = UserData(
  id = id.let(::UserId),
  password = password.let(::UserPassword),
  name = name?.let(::UserName),
  email = email?.let(::UserEmail),
)
