plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.3.1.5724"
    application
    kotlin("jvm") version "2.2.0"
    kotlin("kapt") version "2.2.0"
    checkstyle
    jacoco
}

sonar {
    properties {
        property("sonar.projectKey", "Dimon7091_java-project-78")
        property("sonar.organization", "dmitry-gorbunov-linter")
    }
}

jacoco {
    toolVersion = "0.8.13"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.18.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // отчет зависит от тестов
    reports {
        xml.required.set(true)  // нужен XML-отчет для SonarQube
        html.required.set(true) // полезный HTML-отчет
        csv.required.set(false)
    }
    // если нужно, укажите дополнительные пути к исходникам и классам
}
application {
    mainClass = "hexlet.code.App"
}