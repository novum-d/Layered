package io.novumd.core.data


interface UserRepository {
  fun register()
  fun find(id: UserId)
}