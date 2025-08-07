import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentSelectionWithCurrent

plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.versions)
  application
}

println("Kotlin v${KotlinVersion.CURRENT}")
println("Java v${System.getProperty("java.version")}")
println("Arch: ${System.getProperty("os.arch")}")

group = "github.buriedincode"
version = "0.1.0"

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(libs.jetbrains.annotations)
  implementation(libs.log4j.api)
  
  runtimeOnly(libs.log4j.core)
  
  testImplementation(libs.junit.jupiter)

  testRuntimeOnly(libs.junit.platform)
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

application {
  mainClass = "github.buriedincode.Keschet"
  applicationName = "Keschet"
}

tasks {
  val run by existing(JavaExec::class)
  run.configure {
    standardInput = System.`in`
  }
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
  }
}

spotless {
  java {
    importOrder()
    removeUnusedImports()
    cleanthat()
      .sourceCompatibility("21")
    googleJavaFormat()
    eclipse()
      .sortMembersEnabled(true)
      .sortMembersOrder("SF,F,SI,I,C,SM,M,T")
      .sortMembersVisibilityOrderEnabled(true)
      .sortMembersVisibilityOrder("B,R,D,V")
    leadingTabsToSpaces(2)
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
    version.uppercase().contains(it)
  }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
  gradleReleaseChannel = "current"
  checkForGradleUpdate = true
  checkConstraints = false
  checkBuildEnvironmentConstraints = false
  resolutionStrategy {
    componentSelection {
      all(
        Action<ComponentSelectionWithCurrent> {
          if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
            reject("Release candidate")
          }
        }
      )
    }
  }
}
