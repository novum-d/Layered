import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.test) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.hilt) apply false
  alias(libs.plugins.ksp) apply false
  // alias(libs.plugins.baselineprofile) apply false
  //  alias(libs.plugins.kotlin.serialization) apply false
  //  alias(libs.plugins.dependencyGuard) apply false
  //  alias(libs.plugins.firebase.crashlytics) apply false
  //  alias(libs.plugins.firebase.perf) apply false
  // alias(libs.plugins.gms) apply false
  // alias(libs.plugins.roborazzi) apply false
  // alias(libs.plugins.secrets) apply false
  alias(libs.plugins.room) apply false
  alias(libs.plugins.jetbrainsKotlinAndroid) apply false

  alias(libs.plugins.detekt)
}

allprojects {
  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xcontext-receivers") // Enable the context receiver
    }
  }
}

detekt {
  autoCorrect = true
  buildUponDefaultConfig = true
}
