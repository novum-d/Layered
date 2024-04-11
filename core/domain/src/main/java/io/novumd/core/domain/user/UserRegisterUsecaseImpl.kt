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

    // 1. 入力を検証
    recover({ registerCommand.validate() }) {
      raise(UserInvalid(it))
    }

    // 2. 同じメールアドレスを持つユーザが存在しないことを確認
    existsUser(registerCommand.email.let(::UserEmail))

    // 3. ユーザーを作成
    val user = userRepository.run {
      User.create(
        name = registerCommand.name,
        email = registerCommand.email,
        password = registerCommand.password,
      )
    }

    // 4. 同じIDをもつユーザーが存在しないことを確認
    existsUser(user.id)

    // 5. ユーザを登録
    userRepository.register(user)
  }
}
