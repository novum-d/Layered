package io.novumd.core.data

import arrow.core.raise.Raise
import io.novumd.core.model.UserCreateIdDataError
import io.novumd.core.model.UserFetchDataError
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
  override fun createId(): UserId // 重複確認はサーバー側で行う想定

  context (Raise<UserSaveDataError>)
  fun save(user: User)

  context (Raise<UserFetchDataError>)
  fun fetch(id: UserId): User?

  context (Raise<UserFetchDataError>)
  fun fetch(id: UserEmail): User?
}

internal class UserRepositoryImpl : UserRepository {

  context (Raise<UserRegisterDataError>)
  override fun register(user: User) = TODO()

  context (Raise<UserCreateIdDataError>)
  override fun createId(): UserId = TODO()

  context (Raise<UserSaveDataError>)
  override fun save(user: User) = TODO()

  context (Raise<UserFetchDataError>)
  override fun fetch(id: UserId): User? = null

  context (Raise<UserFetchDataError>)
  override fun fetch(id: UserEmail): User? = null
}