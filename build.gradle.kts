plugins {
    application
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "io.github.plamentotev.xvalid.App"
}

// Storing the dependencies on the file system (and the source code repository)
// is not a good idea but I didn't managed to find those artifacts
// in a public Maven repository that is still maintained.
dependencies {
    runtimeOnly(fileTree(mapOf("dir" to "libs",  "include" to "*.jar")))
}
