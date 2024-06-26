package io.novumd.core.data

import arrow.core.raise.Raise
import io.novumd.core.model.UserCreateIdDataError
import io.novumd.core.model.UserFindDataError
import io.novumd.core.model.UserRegisterDataError
import io.novumd.core.model.UserSaveDataError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserFactoryCommand
import io.novumd.core.model.user.UserId


interface UserRepository : UserFactoryCommand {

  context (Raise<UserRegisterDataError>)
  fun register(user: User)

  context (Raise<UserCreateIdDataError>)
  override fun createId(): UserId

  context (Raise<UserSaveDataError>)
  fun save(user: User)

  context (Raise<UserFindDataError>)
  fun find(id: UserId): User?

  context (Raise<UserFindDataError>)
  fun find(email: UserEmail): User?

  context (Raise<UserFindDataError>)
  fun fetch(): User?
}

internal class UserRepositoryImpl : UserRepository {

  context (Raise<UserRegisterDataError>)
  override fun register(user: User) = run { }

  context (Raise<UserCreateIdDataError>)
  override fun createId(): UserId = "i32".let(::UserId)

  context (Raise<UserSaveDataError>)
  override fun save(user: User) = run { }

  context (Raise<UserFindDataError>)
  override fun find(id: UserId): User? = null

  context(Raise<UserFindDataError>)
  override fun find(email: UserEmail): User? = null

  context (Raise<UserFindDataError>)
  override fun fetch(): User? = null
}