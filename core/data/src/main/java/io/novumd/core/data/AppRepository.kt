package io.novumd.core.data

import arrow.core.raise.Raise
import io.novumd.core.model.UserFindDataError

internal class AppRepositoryImpl : AppRepository {

  context(Raise<UserFindDataError>)
  override fun isAppTamperedWith(): Boolean = false
}

interface AppRepository {

  context(Raise<UserFindDataError>)
  fun isAppTamperedWith(): Boolean
}
