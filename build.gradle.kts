import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.IllegalStateException
import java.util.Properties

plugins {
    id("java")
    `java-library`
    signing
}

group = "io.github.R2turnTrue"
version = "0.0.12"

val publishProps = Properties()
publishProps.load(
    File("publish.properties").inputStream())

ext["signing.keyId"] = publishProps["signing.keyId"]
ext["signing.password"] = publishProps["signing.password"]
ext["signing.secretKeyRingFile"] = publishProps["signing.secretKeyRingFile"]

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly(files("libs/SJ-Core-1.0.5.87.jar"))
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.java-websocket:Java-WebSocket:1.5.5")
    implementation("org.seleniumhq.selenium:selenium-java:4.26.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
    toolchain {
        languageVersion = JavaLanguageVersion.of(16)
    }
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    compileTestJava {
        options.encoding = "UTF-8"
    }
}