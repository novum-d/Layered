package io.novumd.core.model.user

import arrow.core.raise.Raise
import io.novumd.core.model.UserRegistrationError


interface User {
  val id: UserId
  val name: UserName
  val email: UserEmail
  val password: UserPassword

  companion object {

    context(Raise<UserRegistrationError>, UserFactoryCommand)
    fun create(
      name: String,
      email: String,
      password: String,
    ): User =
      UserData(
        id = createId(),
        name = name.let(::UserName),
        email = email.let(::UserEmail),
        password = password.let(::UserPassword),
      )

    fun UserUpdateCommand.asExternalModel(): User =
      UserData(
        id = id.let(::UserId),
        name = name?.let(::UserName) ?: throw IllegalArgumentException("UserName is null."),
        email = email?.let(::UserEmail) ?: throw IllegalArgumentException("UserEmail is null."),
        password = password.let(::UserPassword),
      )
  }
}

private data class UserData(
  override val id: UserId,
  override val name: UserName,
  override val email: UserEmail,
  override val password: UserPassword,
) : User


interface UserFactoryCommand {
  fun createId(): UserId
}

