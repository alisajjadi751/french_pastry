pluginManagement {
    repositories {
        mavenCentral()
       maven ("https://maven.myket.ir")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }

        gradlePluginPortal()
       maven ("https://jitpack.io") // اضافه کردن مخزن JitPack



    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
      maven ("https://maven.myket.ir")
        google()

       maven ("https://jitpack.io") // اضافه کردن مخزن JitPack

    }
}

rootProject.name = "French Pastry"
include(":app")
 