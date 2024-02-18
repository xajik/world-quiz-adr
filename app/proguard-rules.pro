# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


########## Proguard Logs ####
-printmapping build/outputs/mapping/release/mapping.txt
-dump build/outputs/mapping/release/class_files.txt
-printseeds build/outputs/mapping/release/seeds.txt
-printusage build/outputs/mapping/release/unused.txt

-keepattributes Signature

######### World Quiz #######

############ Kotlin ####
-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl
-keep public class kotlin.reflect.jvm.internal.impl.** { public *; }
-keep class com.beust.klaxon.** { *; }
-keep interface com.beust.klaxon.** { *; }
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}
-dontwarn kotlin.**

############ Google ####
-dontwarn com.google.**

############ Facebook ###
-dontwarn com.facebook.**
-keep class com.facebook.** { *; }
-keep class bolts.** { *; }

###### Gson specific classes
-keep class sun.misc.Unsafe { *; }

########## Realm
-dontwarn io.realm
-keep class * extends io.realm.RealmObject

-keepclassmembers class * extends io.realm.RealmObject {
    *;
}

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

############ Logs ###
-assumenosideeffects class android.util.Log {
    public static int d(...);
}