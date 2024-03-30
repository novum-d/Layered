package io.novumd.core.domain

import io.novumd.core.model.user.UserRegisterCommand

interface UserRegisterUsecase {
  operator fun invoke(command: UserRegisterCommand)
}