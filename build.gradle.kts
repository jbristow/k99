plugins {
    kotlin("jvm") version "1.3.50"
}
repositories {
    mavenCentral()
}
group = "dotcomdotorg.net"
version = "1.0-SNAPSHOT"

val arrow_version = "0.10.2"

dependencies {

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.arrow-kt:arrow-core:$arrow_version")
    implementation("io.arrow-kt:arrow-syntax:$arrow_version")
}
