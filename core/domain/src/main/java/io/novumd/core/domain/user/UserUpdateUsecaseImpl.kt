package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import arrow.core.raise.recover
import io.novumd.core.data.AppRepository
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserUpdateUseCase
import io.novumd.core.model.Err
import io.novumd.core.model.UserInvalidError
import io.novumd.core.model.UserRegisterUseCaseError
import io.novumd.core.model.UserUpdateUseCaseError
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserName
import io.novumd.core.model.user.UserPassword
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel
import io.novumd.core.model.user.validate

internal class UserUpdateUseCaseImpl(
  private val userRepository: UserRepository,
  private val appRepository: AppRepository,
  private val existsUserEmail: UserExistsDomainService,
) : UserUpdateUseCase {

  context (Raise<UserUpdateUseCaseError>)
  override fun invoke(updateCommand: UserUpdateCommand) {

    // 1. Validate input.
    recover({ updateCommand.validate() }) {
      raise(UserInvalidError(it))
    }

    // 2. Check whether the app has been tampered with.
    appRepository.checkAppTamperedWith()

    // 3. Fetch a user
    val user = userRepository.fetch() ?: raise(Err.Domain.UserNotFound)

    // 4. Check that the password matches.
    ensure(updateCommand.password == user.password.value) {
      Err.Domain.PasswordNotMatched
    }

    val name = updateCommand.name ?: user.name.value

    // 5. Check that a user with the same email address does not exist.
    val email = updateCommand.email?.let {
      existsUserEmail(it.let(::UserEmail))
      it
    } ?: user.email.value

    // 6. Update the command object.
    updateCommand(name, email)

    // 7. Save the user's changes.
    userRepository.save(updateCommand.asExternalModel(user.id.value))
  }
}

interface UserUseCase {
  context (Raise<UserRegisterUseCaseError>)
  fun registerUser(
    name: UserName,
    email: UserEmail,
    password: UserPassword,
  )

  context (Raise<UserUpdateUseCaseError>)
  fun updateUser(
    name: UserName?,
    email: UserEmail?,
    password: UserPassword,
  )
}
