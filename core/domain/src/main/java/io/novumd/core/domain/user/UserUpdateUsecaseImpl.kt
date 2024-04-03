package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserUpdateUsecase
import io.novumd.core.model.DomainError
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.User.Companion.asExternalModel
import io.novumd.core.model.user.UserId
import io.novumd.core.model.user.UserUpdateCommand

internal class UserUpdateUsecaseImpl(
  private val userRepository: UserRepository,
) : UserUpdateUsecase {

  context (Raise<UserUpdateUsecaseError>)
  override fun invoke(updateCommand: UserUpdateCommand) {
    val userId = updateCommand.id.let(::UserId)
    val user = userRepository.find(userId)

    user.fold(ifEmpty = { raise(DomainError.UserNotFound) }) { usr ->
      ensure(updateCommand.password == usr.password.value) {
        DomainError.PasswordNotMatched
      }

      val name = updateCommand.name ?: usr.name.value
      val email = updateCommand.email ?: usr.email.value

      updateCommand(name, email)

      userRepository.update(updateCommand.asExternalModel())
    }
  }
}