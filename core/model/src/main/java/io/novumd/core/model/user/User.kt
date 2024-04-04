package io.novumd.core.model.user

import arrow.core.raise.Raise
import io.novumd.core.model.UserRegisterDataError


interface User {
  val id: UserId
  val name: UserName
  val email: UserEmail
  val password: UserPassword

  companion object {

    context(Raise<UserRegisterDataError>, UserFactoryCommand)
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

    fun UserUpdateCommand.asExternalModel(): User {
      requireNotNull(name)
      requireNotNull(email)

      return UserData(
        id = id.let(::UserId),
        name = name.let(::UserName),
        email = email.let(::UserEmail),
        password = password.let(::UserPassword),
      )
    }
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

