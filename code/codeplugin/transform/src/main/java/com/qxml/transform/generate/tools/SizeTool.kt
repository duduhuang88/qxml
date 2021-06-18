package com.qxml.transform.generate.tools

import com.qxml.constant.Constants
import java.lang.StringBuilder

class SizeTool {

    companion object {

        private const val MAX_COMPAT_SIZE = 5
        private const val MAX_F_COMPAT_SIZE = -5

        private const val SIZE_OFFSET = "com.qxml.helper.QxmlSize.TYPE_VALUED_COMPLEX_TO_DIMENSION_PIXEL_SIZE_OFFSET"

        @JvmStatic
        fun makeDpFieldFloat(value: String): String {
            return makeDpFieldInt(value) + "f"
        }

        @JvmStatic
        fun makeDpValueFloat(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "float ${makeDpFieldFloat(value)} = 0f"
            }
            return "float ${makeDpFieldFloat(value)} = ${value}f * ${Constants.GEN_FIELD_DENSITY}"
        }

        @JvmStatic
        fun makeDpFieldInt(value: String): String {
            return Constants.GEN_FILED_SIZE_TYPE_DP + value.replace(".", "_").replace("-", "_")
        }

        @JvmStatic
        fun makeDpValueInt(value: String): String {
            val fieldName = makeDpFieldInt(value)
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "int ${makeDpFieldInt(value)} = 0"
            }
            return makeSize(fieldName, value, Constants.GEN_FIELD_DENSITY)
        }

        //sp start ----------------------------------------------
        @JvmStatic
        fun makeSpFieldFloat(value: String): String {
            return makeSpFieldInt(value) + "f"
        }

        @JvmStatic
        fun makeSpValueFloat(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "float ${makeSpFieldFloat(value)} = 0f"
            }
            return "float ${makeSpFieldFloat(value)} = ${value}f * ${Constants.GEN_FIELD_SCALE_DENSITY}"
        }

        @JvmStatic
        fun makeSpFieldInt(value: String): String {
            return Constants.GEN_FILED_SIZE_TYPE_SP + value.replace(".", "_").replace("-", "_")
        }

        @JvmStatic
        fun makeSpValueInt(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "int ${makeSpFieldInt(value)} = 0"
            }
            val fieldName = makeSpFieldInt(value)
            return makeSize(fieldName, value, Constants.GEN_FIELD_SCALE_DENSITY)
        }

        //mm start ----------------------------------------------
        @JvmStatic
        fun makeMMFieldFloat(value: String): String {
            return makeMMFieldInt(value) + "f"
        }

        @JvmStatic
        fun makeMMValueFloat(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "float ${makeMMFieldFloat(value)} = 0f"
            }
            return "float ${makeMMFieldFloat(value)} = ${value}f * ${Constants.GEN_FIELD_X_DPI} * (1.0f/25.4f)"
        }

        @JvmStatic
        fun makeMMFieldInt(value: String): String {
            return Constants.GEN_FILED_SIZE_TYPE_MM + value.replace(".", "_").replace("-", "_")
        }

        @JvmStatic
        fun makeMMValueInt(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "int ${makeMMFieldInt(value)} = 0"
            }
            val fieldName = makeMMFieldInt(value)
            return makeSize(fieldName, value, "${Constants.GEN_FIELD_X_DPI} * (1.0f/25.4f)")
        }

        //pt start ----------------------------------------------
        @JvmStatic
        fun makePTFieldFloat(value: String): String {
            return makePTFieldInt(value) + "f"
        }

        @JvmStatic
        fun makePTValueFloat(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "float ${makePTFieldFloat(value)} = 0f"
            }
            return "float ${makePTFieldFloat(value)} = ${value}f * ${Constants.GEN_FIELD_X_DPI} * (1.0f/72)"
        }

        @JvmStatic
        fun makePTFieldInt(value: String): String {
            return Constants.GEN_FILED_SIZE_TYPE_PT + value.replace(".", "_").replace("-", "_")
        }

        @JvmStatic
        fun makePTValueInt(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "int ${makePTFieldInt(value)} = 0"
            }
            val fieldName = makePTFieldInt(value)

            return makeSize(fieldName, value, "${Constants.GEN_FIELD_X_DPI} * (1.0f/72)")
        }

        //in start ----------------------------------------------
        @JvmStatic
        fun makeINFieldFloat(value: String): String {
            return makeINFieldInt(value) + "f"
        }

        @JvmStatic
        fun makeINValueFloat(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "float ${makeINFieldFloat(value)} = 0f"
            }
            return "float ${makeINFieldFloat(value)} = ${value}f * ${Constants.GEN_FIELD_X_DPI}"
        }

        @JvmStatic
        fun makeINFieldInt(value: String): String {
            return Constants.GEN_FILED_SIZE_TYPE_IN + value.replace(".", "_").replace("-", "_")
        }

        @JvmStatic
        fun makeINValueInt(value: String): String {
            val floatValue = value.toFloat()
            if (floatValue == 0f) {
                return "int ${makeINFieldInt(value)} = 0"
            }
            val fieldName = makeINFieldInt(value)

            return makeSize(fieldName, value, Constants.GEN_FIELD_X_DPI)
        }

        private fun makeSize(fieldName: String, value: String, param: String): String {
            val floatValue = value.toFloat()
            val sb = StringBuilder()
            sb.append("float _float_$fieldName = ${value}f * ${param};")
            if (floatValue > 0) {
                sb.append("\nint $fieldName = (int) (_float_$fieldName + 0.5f);")
            } else if (floatValue < 0) {
                sb.append("\nint $fieldName = (int)  (_float_$fieldName + ${SIZE_OFFSET});")
            }
            if (floatValue <= MAX_COMPAT_SIZE && floatValue >= MAX_F_COMPAT_SIZE) {
                sb.append("\nif ($fieldName == 0) { $fieldName = (_float_$fieldName > 0 ? 1 : -1); }")
            }
            return sb.toString()
        }

    }
}