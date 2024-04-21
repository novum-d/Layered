package io.novumd.core.domain.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import arrow.core.raise.recover
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserUpdateUseCase
import io.novumd.core.model.Err
import io.novumd.core.model.UserInvalid
import io.novumd.core.model.UserRegisterUseCaseError
import io.novumd.core.model.UserUpdateUseCaseError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserName
import io.novumd.core.model.user.UserPassword
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.asExternalModel
import io.novumd.core.model.user.validate

internal class UserUpdateUseCaseImpl(
  private val userRepository: UserRepository,
  private val existsUserEmail: UserExistsDomainService,
) : UserUpdateUseCase {

  context (Raise<UserUpdateUseCaseError>)
  override fun invoke(updateCommand: UserUpdateCommand) {

    // 1. 入力を検証
    recover({ updateCommand.validate() }) {
      raise(UserInvalid(it))
    }

    // 2. ユーザが存在すること確認
    val user = userRepository.load() ?: raise(Err.Domain.UserNotFound)

    // 3. パスワードが一致することを確認
    ensure(updateCommand.password == user.password.value) {
      Err.Domain.PasswordNotMatched
    }

    val name = updateCommand.name ?: user.name.value

    // 4. 同じメールアドレスを持つユーザが存在しないことを確認
    val email = updateCommand.email?.let {
      existsUserEmail(it.let(::UserEmail))
      it
    } ?: user.email.value

    // 5. オプション項目の変更内容を反映
    updateCommand(name, email)

    // 6. ユーザの変更内容を保存
    userRepository.save(updateCommand.asExternalModel(user.id.value))
  }
}

