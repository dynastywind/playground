plugins {
    id("java")
    id("io.freefair.lombok") version "9.5.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(26)
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("org.mybatis:mybatis:3.5.16")
    implementation("com.mysql:mysql-connector-j:9.3.0")
    implementation("com.h2database:h2:2.2.224")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    implementation("commons-io:commons-io:2.19.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}