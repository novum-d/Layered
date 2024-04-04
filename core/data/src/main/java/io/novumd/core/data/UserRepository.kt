package io.novumd.core.data

import arrow.core.Option
import arrow.core.raise.Raise
import io.novumd.core.model.Err
import io.novumd.core.model.UserRegisterDataError
import io.novumd.core.model.UserUpdateDomainError
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

  context (Raise<UserRegisterDataError>)
  override fun register(user: User) = TODO()

  context (Err.DatabaseError)
  override fun createId(): UserId = TODO()

  context (UserUpdateDomainError)
  override fun update(user: User) = TODO()

  context (UserUpdateDomainError)
  override fun find(id: UserId): Option<User> = TODO()
}