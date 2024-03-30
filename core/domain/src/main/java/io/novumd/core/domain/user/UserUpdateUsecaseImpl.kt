package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserUpdateUsecase
import io.novumd.core.model.DomainError
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.UserId
import io.novumd.core.model.user.UserUpdateCommand

internal class UserUpdateUsecaseImpl(
  private val userRepository: UserRepository,
) : UserUpdateUsecase {


  context (Raise<UserUpdateUsecaseError>)
  override fun invoke(command: UserUpdateCommand) {
    val userId = command.id.let(::UserId)
    val user = userRepository.find(userId)
    user.onNone { raise(DomainError.UserNotFound) }
  }
}