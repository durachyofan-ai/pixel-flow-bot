package com.example.pixelflowbot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class PixelFlowAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        serviceInfo = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED or
                AccessibilityEvent.TYPE_VIEW_CLICKED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS or
                AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
            packageNames = arrayOf("com.loomgames.pixelflow")
        }
        Log.d("PixelFlowBot", "Accessibility service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val packageName = event.packageName?.toString() ?: "unknown"
        Log.d("PixelFlowBot", "Event: ${event.eventType} package=$packageName")

        if (packageName == "com.loomgames.pixelflow") {
            val root = rootInActiveWindow ?: return
            inspectNode(root, 0)
        }
    }

    override fun onInterrupt() {
        Log.d("PixelFlowBot", "Accessibility service interrupted")
    }

    fun clickByText(targetText: String) {
        val root = rootInActiveWindow ?: return
        val node = findNodeByText(root, targetText) ?: return
        node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    private fun inspectNode(node: AccessibilityNodeInfo, depth: Int) {
        val text = node.text?.toString()
        if (text != null && text.isNotEmpty()) {
            Log.d("PixelFlowBot", "Node depth=$depth text=$text")
        }

        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            inspectNode(child, depth + 1)
        }
    }

    private fun findNodeByText(node: AccessibilityNodeInfo, target: String): AccessibilityNodeInfo? {
        if (node.text?.toString() == target) {
            return node
        }

        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            val found = findNodeByText(child, target)
            if (found != null) return found
        }

        return null
    }
}
