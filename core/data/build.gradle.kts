plugins {
  alias(libs.plugins.layered.android.library)
  // alias(libs.plugins.layered.android.library.jacoco)
  alias(libs.plugins.layered.android.hilt)
  // id("kotlinx-serialization")
}

android {
  namespace = "io.novumd.layered.core.data"
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

dependencies {
  project
  // api(projects.core.common)
  // api(projects.core.database)
  // api(projects.core.datastore)
  api(projects.core.network)

  // implementation(projects.core.analytics)
  // implementation(projects.core.notifications)

  testImplementation(libs.kotlinx.coroutines.test)
  // testImplementation(libs.kotlinx.serialization.json)
  // testImplementation(projects.core.datastoreTest)
  // testImplementation(projects.core.testing)
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
  kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
}
