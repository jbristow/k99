plugins {
    kotlin("jvm") version "1.3.50"
}
repositories {
    mavenCentral()
}
group = "dotcomdotorg.net"
version = "1.0-SNAPSHOT"

dependencies {

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
}