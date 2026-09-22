package com.example.pixelflowbot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class PixelFlowAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        serviceInfo = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS or AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
            packageNames = arrayOf("com.loomgames.pixelflow")
        }
        Log.d("PixelFlowBot", "Accessibility service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // This is intentionally light: during testing, this is where we can parse the current UI tree.
        // Real detection logic should be added after device-level validation.
        if (event == null) return
        Log.d("PixelFlowBot", "Event: ${event.eventType} package=${event.packageName}")
    }

    override fun onInterrupt() {
        Log.d("PixelFlowBot", "Accessibility service interrupted")
    }

    fun clickNodeByText(text: String) {
        val root = rootInActiveWindow ?: return
        findNodeByText(root, text)?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    private fun findNodeByText(node: AccessibilityNodeInfo, target: String): AccessibilityNodeInfo? {
        if (node.text?.toString() == target) return node
        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            val result = findNodeByText(child, target)
            if (result != null) return result
        }
        return null
    }
}
