package io.novumd.core.data

import arrow.core.raise.Raise
import io.novumd.core.model.UserCreateIdDataError
import io.novumd.core.model.UserFindDataError
import io.novumd.core.model.UserRegisterDataError
import io.novumd.core.model.UserSaveDataError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserFactoryCommand
import io.novumd.core.model.user.UserId


interface UserRepository : UserFactoryCommand {
  context (Raise<UserRegisterDataError>)
  fun register(user: User)

  context (Raise<UserCreateIdDataError>)
  override fun createId(): UserId // 重複確認はサーバー側で行う想定

  context (Raise<UserSaveDataError>)
  fun save(user: User)

  context (Raise<UserFindDataError>)
  fun find(id: UserId): User?
}

internal class UserRepositoryImpl : UserRepository {

  override fun register(user: User) = TODO()

  override fun createId(): UserId = TODO()

  override fun save(user: User) = TODO()

  override fun find(id: UserId): User? = null
}