package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserUpdateUsecase
import io.novumd.core.model.Err
import io.novumd.core.model.UserUpdateDomainError
import io.novumd.core.model.user.UserId
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel

internal class UserUpdateUsecaseImpl(
  private val userRepository: UserRepository,
) : UserUpdateUsecase {

  context (Raise<UserUpdateDomainError>)
  override fun invoke(command: UserUpdateCommand) {
    val userId = command.id.let(::UserId)
    val user = userRepository.find(userId)

    user.fold(ifEmpty = { raise(Err.UserNotFound) }) { usr ->
      ensure(command.password == usr.password.value) {
        Err.PasswordNotMatched
      }

      val name = command.name ?: usr.name.value
      val email = command.email ?: usr.email.value

      command(name, email)

      userRepository.update(command.asExternalModel())
    }
  }
}