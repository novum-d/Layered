package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserUpdateUsecase
import io.novumd.core.model.Err
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.UserId
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel

internal class UserUpdateUsecaseImpl(
  private val userRepository: UserRepository,
  private val userExistsDomainService: UserExistsDomainService,
) : UserUpdateUsecase {

  context (Raise<UserUpdateUsecaseError>)
  override fun invoke(command: UserUpdateCommand) {
    val userId = command.id.let(::UserId)
    userExistsDomainService(userId)

    val user = userRepository.find(userId) ?: raise(Err.UserNotFound)

    ensure(command.password == user.password.value) {
      Err.PasswordNotMatched
    }

    val name = command.name ?: user.name.value
    val email = command.email ?: user.email.value

    command(name, email)

    userRepository.save(command.asExternalModel())
  }
}