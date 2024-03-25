plugins {
  alias(libs.plugins.layered.jvm.library)
  alias(libs.plugins.ksp)
}

dependencies {
  api(libs.kotlinx.datetime)
  api(libs.arrow.core)

  implementation(libs.arrow.core)
  implementation(libs.arrow.optics)
  ksp(libs.arrow.opticsPlugin)
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
  kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
}
