package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.recover
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.UserInvalid
import io.novumd.core.model.UserRegisterUsecaseError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserRegisterCommand
import io.novumd.core.model.user.validate


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val existsUser: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<UserRegisterUsecaseError>)
  override fun invoke(registerCommand: UserRegisterCommand) {

    // validate input
    recover({ registerCommand.validate() }) {
      raise(UserInvalid(it))
    }

    val email = registerCommand.email.let(::UserEmail)

    existsUser(email)

    val user = userRepository.run {
      User.create(
        name = registerCommand.name,
        email = registerCommand.email,
        password = registerCommand.password,
      )
    }

    existsUser(user.id)

    userRepository.register(user)
  }
}
