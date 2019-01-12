object App {
    val id = "ir.alirezaiyan.moviz"
    val compileSdk = 28
    val minSdk = 19
    val minSdkTv = 21
    val targetSdk = 28
    val versionCode = 1
    val versionName = "1.0"
}

object GradleDir {
    val kotlin = "../common-kotlin-library.gradle"
    val android = "../common-android-library.gradle"
}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Modules {
    val app = ":app"
    val data = ":data"
    val domain = ":domain"
    val sdkBase = ":sdk-base"
    val sdkPlatform = ":sdk-platform"
}

object Versions {
    val gradle = "3.2.1"

    val appcompat = "1.0.0"
    val archComponents_version = "2.0.0-rc01"

    val constraint = "1.1.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"

    val ktx = "1.0.0-alpha1"


    val kotlin = "1.3.0"
    val kotlinCoroutines = "1.0.1"
    val timber = "4.7.1"
    val retrofit = "2.5.0"
    val okHttp = "3.12.0"
    val loggingInterceptor = "3.12.0"
    val moshi = "1.8.0"
    val lifecycle = "2.0.0"
    val leakCanary = "1.6.2"
    val koin = "1.0.0"
    val gson = "2.8.5"

    val junit = "4.12"
    val mockito = "2.18.3"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.8.9"
    val robolectric_version = "3.8"
    val kluent_version = "1.14"

    val javaxInject_version = "1"
    val javaxAnnotations_version = "1.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"


    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val okhttp = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val leakCanaryAndroidNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    val leakCanaryAndroidSupportFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"

    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinCore = "org.koin:koin-core:${Versions.koin}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject_version}"
    val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotations_version}"

}

object SupportLibraries {
    val archComponents = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents_version}"

    val leanback = "androidx.leanback:leanback:${Versions.appcompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}

object TestLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val junit = "junit:junit:${Versions.junit}"
    val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    val runner = "androidx.test:runner:1.1.0"
    val rules = "androidx.test:rules:1.1.0"
    val espressoCore = "androidx.test.espresso:espresso-core:3.1.0"
    val espressoIntent = "androidx.test.espresso:espresso-intents:3.1.0"
    val xjunit = "androidx.test.ext:junit:1.0.0"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric_version}"
    val kluent = "org.amshove.kluent:kluent:${Versions.kluent_version}"
    val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

}