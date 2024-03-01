plugins {
  alias(libs.plugins.layered.android.library)
  alias(libs.plugins.layered.android.library.compose)
  alias(libs.plugins.layered.android.hilt)
}

android {
  namespace = "com.google.samples.apps.nowinandroid.core.testing"
}

dependencies {
  api(kotlin("test"))
  api(libs.androidx.compose.ui.test)
  // api(libs.roborazzi)
  // api(projects.core.analytics)
  // api(projects.core.data)
  // api(projects.core.model)
  // api(projects.core.notifications)

  debugApi(libs.androidx.compose.ui.testManifest)

  implementation(libs.accompanist.testharness)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.test.rules)
  implementation(libs.hilt.android.testing)
  implementation(libs.kotlinx.coroutines.test)
  implementation(libs.kotlinx.datetime)
  // implementation(libs.robolectric.shadows)
  // implementation(projects.core.common)
  // implementation(projects.core.designsystem)
}
