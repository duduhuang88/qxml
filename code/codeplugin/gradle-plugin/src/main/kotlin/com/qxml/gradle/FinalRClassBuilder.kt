package com.qxml.gradle

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import java.util.Locale
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC


internal const val ATTR = "attr"
internal val SUPPORTED_TYPES = setOf(ATTR)

/**
 * Generates attr_reference_test class that contains all supported field names in an R file as final values.
 * Also enables adding support annotations to indicate the type of resource for every field.
 */
class FinalRClassBuilder(
  private val packageName: String,
  private val className: String
) {

  private var resourceTypes = mutableMapOf<String, TypeSpec.Builder>()

  fun build(): JavaFile {
    val result = TypeSpec.classBuilder(className)
        .addModifiers(PUBLIC, FINAL)
    for (type in SUPPORTED_TYPES) {
      resourceTypes.get(type)?.let {
        result.addType(it.build())
      }
    }
    return JavaFile.builder(packageName, result.build())
        .addFileComment("Generated code from QXML gradle plugin. Do not modify!")
        .build()
  }

  fun addResourceField(type: String, fieldName: String, fieldInitializer: CodeBlock, doc: String) {
    if (type != ATTR) {
      return
    }
    val fieldSpecBuilder = FieldSpec.builder(String::class.java, fieldName)
        .addModifiers(PUBLIC, STATIC, FINAL)
        .initializer(fieldInitializer)

    fieldSpecBuilder.addJavadoc("\$L", doc)

    val resourceType =
        resourceTypes.getOrPut(type) {
          TypeSpec.classBuilder(type).addModifiers(PUBLIC, STATIC, FINAL)
        }
    resourceType.addField(fieldSpecBuilder.build())
  }

  // TODO https://youtrack.jetbrains.com/issue/KT-28933
  private fun String.capitalize(locale: Locale) = substring(0, 1).toUpperCase(locale) + substring(1)
}
