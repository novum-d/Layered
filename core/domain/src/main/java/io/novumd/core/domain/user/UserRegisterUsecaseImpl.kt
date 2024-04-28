package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.recover
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUseCase
import io.novumd.core.model.UserInvalidError
import io.novumd.core.model.UserRegisterUseCaseError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserRegisterCommand
import io.novumd.core.model.user.validate


internal class UserRegisterUseCaseImpl(
  private val userRepository: UserRepository,
  private val existsUser: UserExistsDomainService,
) : UserRegisterUseCase {

  context (Raise<UserRegisterUseCaseError>)
  override fun invoke(registerCommand: UserRegisterCommand) {

    // 1. Validate input
    recover({ registerCommand.validate() }) {
      raise(UserInvalidError(it))
    }

    // 2. Check that a user with the same email address does not exist.
    existsUser(registerCommand.email.let(::UserEmail))

    // 3. Create a user.
    val user = userRepository.run {
      User.create(
        name = registerCommand.name,
        email = registerCommand.email,
        password = registerCommand.password,
      )
    }

    // 4. Check that a user with the same ID does not exist.
    existsUser(user.id)

    // 5. Register a user.
    userRepository.register(user)
  }
}
