# detekt

## Metrics

* 0 number of properties

* 4 number of functions

* 4 number of classes

* 1 number of packages

* 2 number of kt files

## Complexity Report

* 29 lines of code (loc)

* 17 source lines of code (sloc)

* 10 logical lines of code (lloc)

* 5 comment lines of code (cloc)

* 4 cyclomatic complexity (mcc)

* 0 cognitive complexity

* 3 number of total code smells

* 29% comment source ratio

* 400 mcc per 1,000 lloc

* 300 code smells per 1,000 lloc

## Findings (3)

### naming, PackageNaming (2)

Package names should match the naming convention set in the configuration.

[Documentation](https://detekt.dev/docs/rules/naming#packagenaming)

* C:/Users/goykhman/AndroidStudioProjects/TranslAppTestTask/module-injector/src/main/java/com/example/module_injector/ComponentHolder.kt:1:1
```
Package name should match the pattern: [a-z]+(\.[a-z][A-Za-z0-9]*)*
```
```kotlin
1 package com.example.module_injector
! ^ error
2 
3 interface ComponentHolder<C : BaseApi, D : BaseDependencies>{
4     fun init(dependencies: D)

```

* C:/Users/goykhman/AndroidStudioProjects/TranslAppTestTask/module-injector/src/test/java/com/example/module_injector/ExampleUnitTest.kt:1:1
```
Package name should match the pattern: [a-z]+(\.[a-z][A-Za-z0-9]*)*
```
```kotlin
1 package com.example.module_injector
! ^ error
2 
3 import org.junit.Test
4 

```

### style, NewLineAtEndOfFile (1)

Checks whether files end with a line separator.

[Documentation](https://detekt.dev/docs/rules/style#newlineatendoffile)

* C:/Users/goykhman/AndroidStudioProjects/TranslAppTestTask/module-injector/src/test/java/com/example/module_injector/ExampleUnitTest.kt:17:2
```
The file C:\Users\goykhman\AndroidStudioProjects\TranslAppTestTask\module-injector\src\test\java\com\example\module_injector\ExampleUnitTest.kt is not ending with a new line.
```
```kotlin
14     fun addition_isCorrect() {
15         assertEquals(4, 2 + 2)
16     }
17 }
!!  ^ error

```

generated with [detekt version 1.21.0-RC2](https://detekt.dev/) on 2022-07-08 09:03:14 UTC
