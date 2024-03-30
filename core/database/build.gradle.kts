plugins {
  alias(libs.plugins.layered.android.library)
  // alias(libs.plugins.layered.android.library.jacoco)
  alias(libs.plugins.layered.android.hilt)
  alias(libs.plugins.layered.android.room)
}

android {
}

android {
  defaultConfig {
    // testInstrumentationRunner =
    //   "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
  }
  namespace = "io.novumd.layered.core.database"
}

dependencies {
  api(projects.core.model)

  implementation(libs.kotlinx.datetime)

  // androidTestImplementation(projects.core.testing)
}
