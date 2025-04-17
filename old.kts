plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id ("org.hidetake.ssh") version "2.11.2"
}

group = "it.ds"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenLocal()
	mavenCentral()
	google()
	maven {
		url = uri("https://repo.spring.io/milestone")
	}
}

buildscript {
	repositories {
		mavenLocal()
		mavenCentral()
		google()
		maven {
			url = uri("https://repo.spring.io/milestone")
		}
		maven {
			url = uri("https://plugins.gradle.org/m2/")
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.flywaydb:flyway-core:11.6.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
	implementation ("io.jsonwebtoken:jjwt-api:0.12.6")
	implementation("org.mapstruct:mapstruct:1.6.3")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:11.6.0")
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.12.6")
	runtimeOnly("org.postgresql:postgresql")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}
