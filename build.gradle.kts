import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.7.10"
    java
    kotlin("jvm") version kotlinVersion
    //kotlin("plugin.spring") version kotlinVersion
    jacoco
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion

    id("org.springframework.boot") version "2.4.8"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    //id("org.jetbrains.kotlin.jvm") version kotlinVersion

    //support Idea IDE
    id("idea")
    id("io.freefair.lombok") version "6.6.3"
    id("io.freefair.aspectj.post-compile-weaving") version "6.6.3"

    // Build uber-jar
    id("com.github.johnrengelman.shadow") version "6.1.0" apply false
    id("maven-publish")

}

extra["kotlin.version"] = "1.7.10"


System.getProperties()

val allureVersion = "2.8.0"
val junit5Version = "5.8.1"
val springVersion = "5.3.0"
val junitJupiterVersion = "5.7.0"
val junitPlatformVersion = "1.7.0-M1"
val jacksonVersion = "2.12.5"
val aspectJweaverVersion = "1.9.5"
val gsonVersion = "2.7"
val restAssuredVersion = "4.3.3"
val testrailVersion = "2.0.1"
val awsJavaSdkVersion = "1.12.65"
val jsonpathVersion = "3.0.6"
val jsonSchemaValidatorVersion = "4.3.0"
val libphonenumberVersion = "8.12.34"
val validatorVersion = "1.7"
val sqsVersion = "1.0.8"
val springBootVersion = "2.6.3"

version = "1.0.0-SNAPSHOT"
//group = "ru.pimalex.app" //это для примера

val javaVersion = JavaVersion.VERSION_11

java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

//buildscript {
//    repositories {
//        maven {
//            url = uri("https://plugins.gradle.org/m2/")
//        }
//    }
//    dependencies {
//        classpath("io.freefair.gradle:aspectj-plugin:6.6.3")
//    }
//}

apply(plugin = "io.freefair.aspectj.post-compile-weaving")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {

    //dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.freefair.aspectj.post-compile-weaving:io.freefair.aspectj.post-compile-weaving.gradle.plugin:6.6.3")

//    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude(mapOf("group" to "org.junit.vintage", "module" to "junit-vintage-engine"))
//    }
    //    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    //}
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")

    //Kotlin
//    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    runtimeOnly("org.jetbrains.kotlin:kotlin-bom")

    //Test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(mapOf("group" to "org.junit.vintage", "module" to "junit-vintage-engine"))
    }
//    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude(module = "junit")
//    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


    // JUnit
    implementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    implementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    implementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    implementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    implementation("org.junit.platform:junit-platform-runner:$junitPlatformVersion")

    // spring
//    implementation platform ("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    //implementation //"org.springframework.boot:spring-boot-starter"
//    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude group : "org.junit.vintage", module: "junit-vintage-engine"
//        exclude group : "org.springframework.boot", module: "spring-boot-starter-logging"
//    }
    implementation("org.springframework:spring-context:$springVersion")
    implementation("org.springframework:spring-test:$springVersion")
    implementation("org.springframework.cloud:spring-cloud-aws-core:2.2.6.RELEASE")

    // ser-deser
    implementation("com.google.code.gson:gson:$gsonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    // allures
    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")
    implementation("io.qameta.allure:allure-generator:$allureVersion")
    implementation("org.aspectj:aspectjweaver:$aspectJweaverVersion")
    testRuntimeOnly("io.qameta.allure:allure-junit5:$allureVersion")

    //REST client
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$jsonpathVersion")
    implementation("io.rest-assured:json-schema-validator:$jsonSchemaValidatorVersion")

    //TestRail
    implementation("com.codepine.api:testrail-api-java-client:$testrailVersion")

    //AWS
    implementation("com.amazonaws:aws-java-sdk-s3:$awsJavaSdkVersion")
    implementation("com.amazonaws:aws-java-sdk-core:$awsJavaSdkVersion")

    //validators
    //phones
    implementation("com.googlecode.libphonenumber:libphonenumber:$libphonenumberVersion")
    //emails and links
    implementation("commons-validator:commons-validator:$validatorVersion")

    implementation("javax.xml.bind:jaxb-api:2.2.4")

    //sqs
    implementation("com.amazonaws:amazon-sqs-java-messaging-lib:$sqsVersion")

    //еще немного библиотек
    implementation("com.google.guava:guava:17.0")
    implementation("org.apache.commons:commons-email:1.5")
}

//the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
//    imports {
//        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//        //mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
//    }
//}

dependencyManagement {
    dependencies {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
}

tasks.withType<KotlinCompile>{
    kotlinOptions{
        jvmTarget = javaVersion.toString()
    }
}

tasks.test {
    useJUnitPlatform()
}