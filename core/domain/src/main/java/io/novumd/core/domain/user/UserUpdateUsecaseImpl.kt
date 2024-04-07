package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import arrow.core.raise.recover
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserUpdateUsecase
import io.novumd.core.model.Err
import io.novumd.core.model.UserInvalid
import io.novumd.core.model.UserUpdateUsecaseError
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserId
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel
import io.novumd.core.model.user.validate

internal class UserUpdateUsecaseImpl(
  private val userRepository: UserRepository,
  private val existsUserEmail: UserExistsDomainService,
) : UserUpdateUsecase {

  context (Raise<UserUpdateUsecaseError>)
  override fun invoke(updateCommand: UserUpdateCommand) {

    // validate input
    recover({ updateCommand.validate() }) {
      raise(UserInvalid(it))
    }

    val userId = updateCommand.id.let(::UserId)
    val user = userRepository.fetch(userId) ?: raise(Err.Domain.UserNotFound)

    ensure(updateCommand.password == user.password.value) {
      Err.Domain.PasswordNotMatched
    }

    val name = updateCommand.name ?: user.name.value
    val email = updateCommand.email?.let {
      existsUserEmail(it.let(::UserEmail))
      it
    } ?: user.email.value

    updateCommand(name, email)

    userRepository.save(updateCommand.asExternalModel())
  }
}