package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import arrow.core.raise.recover
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.AppTamperedWithDomainService
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserUpdateUseCase
import io.novumd.core.model.UserInputError
import io.novumd.core.model.UserUpdateUseCaseError
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel
import io.novumd.core.model.user.validate

internal class UserUpdateUseCaseImpl(
  private val userRepository: UserRepository,
  private val checkAppTamperedWith: AppTamperedWithDomainService,
  private val existsUserEmail: UserExistsDomainService,
) : UserUpdateUseCase {

  context (Raise<UserUpdateUseCaseError>)
  override fun invoke(updateCommand: UserUpdateCommand) {

    // 1. Validate input.
    recover({ updateCommand.validate() }) {
      UserInputError(it)
    }

    // 2. Check whether the app has been tampered with.
    checkAppTamperedWith()

    // 3. Fetch a user
    val user = userRepository.fetch() ?: raise(DomainErr.UserNotFound)

    // 4. Check that the password matches.
    ensure(updateCommand.password == user.password.value) {
      DomainErr.PasswordNotMatched
    }

    val name = updateCommand.name ?: user.name?.value

    // 5. Check that a user with the same email address does not exist.
    val email = updateCommand.email?.let {
      existsUserEmail(it.let(::UserEmail))
      it
    } ?: user.email?.value

    // 6. Update the command object.
    updateCommand(name, email)

    // 7. Save the user's changes.
    userRepository.save(updateCommand.asExternalModel(user.id.value))
  }
}
