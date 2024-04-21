package io.novumd.core.domain

import arrow.core.raise.Raise
import io.novumd.core.model.UserUpdateUseCaseError
import io.novumd.core.model.user.UserUpdateCommand

fun interface UserUpdateUseCase {
  context (Raise<UserUpdateUseCaseError>)
  operator fun invoke(updateCommand: UserUpdateCommand)
}