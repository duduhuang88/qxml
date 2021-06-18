package com.qxml.gradle

import com.squareup.javapoet.CodeBlock
import java.io.File

internal class ResourceSymbolListReader(private val builder: FinalRClassBuilder) {

  fun readSymbolTable(symbolTable: File) {
    symbolTable.forEachLine { processLine(it) }
  }

  private fun processLine(line: String) {
    val values = line.split(' ')
    if (values.size < 4) {
      return
    }
    val javaType = values[0]
    if (javaType != "int") {
      return
    }
    val symbolType = values[1]
    if (symbolType != ATTR) {
      return
    }
    val name = values[2]
    val value = CodeBlock.of("\$S", name)
    builder.addResourceField(symbolType, name, value, "{@link R.attr.$name}")
  }
}
