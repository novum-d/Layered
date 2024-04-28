package io.novumd.core.data

import arrow.core.raise.Raise
import io.novumd.core.model.AppTamperedWithDataError

internal class AppRepositoryImpl : AppRepository {

  context(Raise<AppTamperedWithDataError>)
  override fun checkAppTamperedWith() {
    // If the app has been tampered with, raise an error type
  }
}

interface AppRepository {

  context(Raise<AppTamperedWithDataError>)
  fun checkAppTamperedWith()
}
