package io.novumd.core.domain

import arrow.core.raise.Raise
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.UserUpdateCommand

fun interface UserUpdateUsecase {
  context (Raise<UserUpdateUsecaseError>)
  operator fun invoke(updateCommand: UserUpdateCommand)
}