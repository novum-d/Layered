plugins {
  alias(libs.plugins.layered.android.library)
  // alias(libs.plugins.layered.android.library.jacoco)
  alias(libs.plugins.layered.android.hilt)
}

android {
  namespace = "io.novumd.layered.core.common"
}

dependencies {
  // testImplementation(libs.kotlinx.coroutines.test)
  // testImplementation(libs.turbine)
}
