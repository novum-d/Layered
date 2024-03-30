package io.novumd.core.domain

import io.novumd.core.model.user.UserId

fun interface UserExistsDomainService {
  operator fun invoke(id: UserId): Boolean
}