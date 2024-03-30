package io.novumd.core.domain

import io.novumd.core.model.user.UserUpdateCommand

fun interface UserUpdateUsecase {
  operator fun invoke(command: UserUpdateCommand)
}