package com.rainbow.entity

import jodd.datetime.JDateTime
import org.apache.poi.ss.usermodel.*
import org.springframework.web.servlet.view.document.AbstractXlsxView
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExcelView : AbstractXlsxView() {

    private fun getStyle(workbook: Workbook, isBold: Boolean = false): CellStyle {
        val style = workbook.createCellStyle()
        val font = workbook.createFont()
        font.bold = isBold
        font.fontName = "宋体"
        style.setFont(font)
        style.setAlignment(HorizontalAlignment.CENTER)
        style.setVerticalAlignment(VerticalAlignment.CENTER)

        return style
    }

    override fun buildExcelDocument(model: MutableMap<String, Any>, workbook: Workbook, request: HttpServletRequest, response: HttpServletResponse) {
        response.setHeader("Content-Disposition", "attachment;filename=${JDateTime().toString("YYYYMMDDhhmmss")}.xlsx")

        val sheetName = model.getOrDefault("sheetName", "工作表1") as String
        val headers = model.getOrDefault("headers", emptyList<Any>()) as List<*>
        val data = model.getOrDefault("data", emptyList<Any>()) as List<*>
        val columnWidth = model.getOrDefault("columnWidth", emptyMap<Int, Int>()) as Map<*, *>

        val sheet = workbook.createSheet(sheetName)
        var currentRow = 0
        var currentColumn = 0

        val headerStyle = getStyle(workbook, true)

        val headerRow = sheet.createRow(currentRow)
        headerRow.heightInPoints = 30f
        headers.forEach {
            val cell = headerRow.createCell(currentColumn)
            cell.cellStyle = headerStyle
            cell.setCellValue(it as String)
            if (it.toString().length > 3) {
                sheet.setColumnWidth(currentColumn, (it.length * 2 + 2) * 268)
            }
            currentColumn++
        }

        val cellStyle = getStyle(workbook)
        data.forEach {
            currentRow++
            currentColumn = 0
            val row = sheet.createRow(currentRow)
            row.heightInPoints = 25f
            it as List<*>
            it.forEach {
                val cell = row.createCell(currentColumn)
                cell.cellStyle = cellStyle
                when (it) {
                    is Int -> {
                        cell.setCellType(CellType.NUMERIC)
                        cell.setCellValue(it.toString())
                    }
                    is Double -> cell.setCellValue(it)
                    is Date -> cell.setCellValue(it)
                    is Boolean -> cell.setCellValue(it)
                    else -> cell.setCellValue((it ?: "").toString())
                }
                if (columnWidth.containsKey(currentColumn)) {
                    val width = (columnWidth[currentColumn] as Int) * 268
                    if (width > sheet.getColumnWidth(currentColumn)) {
                        sheet.setColumnWidth(currentColumn, width)
                    }
                }
                currentColumn++
            }
        }
    }
}
