/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "io.novumd.layered.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.android.tools.common)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
  // compileOnly(libs.room.gradlePlugin)
  // implementation(libs.truth)
  // compileOnly(libs.firebase.crashlytics.gradlePlugin)
  // compileOnly(libs.firebase.performance.gradlePlugin)
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "layered.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidApplication") {
      id = "layered.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    // register("androidApplicationJacoco") {
    //   id = "layered.android.application.jacoco"
    //   implementationClass = "AndroidApplicationJacocoConventionPlugin"
    // }
    register("androidLibraryCompose") {
      id = "layered.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "layered.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidFeature") {
      id = "layered.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
    // register("androidLibraryJacoco") {
    //   id = "layered.android.library.jacoco"
    //   implementationClass = "AndroidLibraryJacocoConventionPlugin"
    // }
    register("androidTest") {
      id = "layered.android.test"
      implementationClass = "AndroidTestConventionPlugin"
    }
    register("androidHilt") {
      id = "layered.android.hilt"
      implementationClass = "AndroidHiltConventionPlugin"
    }
    // register("androidRoom") {
    //   id = "layered.android.room"
    //   implementationClass = "AndroidRoomConventionPlugin"
    // }
    // register("androidFirebase") {
    //   id = "layered.android.application.firebase"
    //   implementationClass = "AndroidApplicationFirebaseConventionPlugin"
    // }
    register("androidFlavors") {
      id = "layered.android.application.flavors"
      implementationClass = "AndroidApplicationFlavorsConventionPlugin"
    }
    register("androidLint") {
      id = "layered.android.lint"
      implementationClass = "AndroidLintConventionPlugin"
    }
    register("jvmLibrary") {
      id = "layered.jvm.library"
      implementationClass = "JvmLibraryConventionPlugin"
    }
  }
}
