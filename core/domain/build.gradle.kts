plugins {
  alias(libs.plugins.layered.android.library)
  // alias(libs.plugins.layered.android.library.jacoco)
  id("com.google.devtools.ksp")
}

android {
  namespace = "io.novumd.layered.core.domain"
}

dependencies {
  api(projects.core.data)
  api(projects.core.model)

  implementation(libs.javax.inject)

  // testImplementation(projects.core.testing)
}
