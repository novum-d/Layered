package io.novumd.core.model.user

import arrow.core.raise.Raise
import io.novumd.core.model.UserRegistrationError


interface User {
  val id: UserId
  val name: UserName
  val password: UserPassword

  companion object {

    context(Raise<UserRegistrationError>, UserFactoryCommand)
    fun create(
      name: String,
      password: String,
    ): User =
      UserData(
        id = createId(),
        name = name.let(::UserName),
        password = password.let(::UserPassword),
      )

    fun from(
      id: String,
      name: String,
      password: String,
    ): User =
      UserData(
        id = id.let(::UserId),
        name = name.let(::UserName),
        password = password.let(::UserPassword),
      )
  }
}

private data class UserData(
  override val id: UserId,
  override val name: UserName,
  override val password: UserPassword,
) : User


interface UserFactoryCommand {
  fun createId(): UserId
}

