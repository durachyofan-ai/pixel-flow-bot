package com.example.pixelflowbot

import android.graphics.Point
import android.graphics.Rect
import kotlin.math.abs

/**
 * This is a prototype solver scaffold for Pixel Flow.
 * It models the board as a grid of colors and a pool of tiles / keys.
 *
 * The real implementation requires device-level testing on Pixel Flow to calibrate:
 * 1) board boundaries
 * 2) color samples
 * 3) tile positions and key tiles
 * 4) clickable action coordinates
 */
class PixelFlowSolver {

    data class Cell(val color: Int, val isKey: Boolean = false)

    fun solveBoard(board: Array<IntArray>, availableColors: List<Int>): List<String> {
        // Placeholder: real solver should parse board and produce action list.
        // For now we just return empty list to avoid incorrect automation.
        return emptyList()
    }

    fun detectBoardBounds(bitmap: android.graphics.Bitmap): Rect {
        return Rect(0, 0, bitmap.width, bitmap.height)
    }

    fun sampleColorAt(point: Point): Int {
        // Replace with real bitmap sampling after OCR/vision calibration.
        return 0
    }

    fun findMatchingMoves(board: Array<IntArray>, color: Int): List<Point> {
        val found = mutableListOf<Point>()
        val rows = board.size
        val cols = board[0].size

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (board[r][c] == color) {
                    found.add(Point(c, r))
                }
            }
        }
        return found
    }

    fun isSafeMove(board: Array<IntArray>, point: Point): Boolean {
        val row = point.y
        val col = point.x
        if (row < 0 || col < 0) return false
        if (row >= board.size || col >= board[0].size) return false
        return board[row][col] != 0
    }
}
