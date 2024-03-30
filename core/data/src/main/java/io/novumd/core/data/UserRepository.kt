package io.novumd.core.data

import arrow.core.Option
import arrow.core.raise.Raise
import io.novumd.core.model.DomainError
import io.novumd.core.model.UserRegistrationError
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserFactoryCommand
import io.novumd.core.model.user.UserId


interface UserRepository : UserFactoryCommand {
  fun register(user: User)

  override fun createId(): UserId

  fun update(user: User)

  fun find(id: UserId): Option<User>
}

internal class UserRepositoryImpl : UserRepository {

  context (Raise<UserRegistrationError>)
  override fun register(user: User) = TODO()

  context (DomainError.DatabaseError)
  override fun createId(): UserId = TODO()

  context (UserUpdateUsecaseError)
  override fun update(user: User) = TODO()

  context (UserUpdateUsecaseError)
  override fun find(id: UserId): Option<User> = TODO()
}