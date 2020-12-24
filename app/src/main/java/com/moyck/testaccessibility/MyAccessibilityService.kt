package com.moyck.testaccessibility

import android.accessibilityservice.AccessibilityService
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


class MyAccessibilityService : AccessibilityService() {

    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        try {
            //拿到根节点
            val rootInfo = rootInActiveWindow ?: return
            //开始找目标节点，这里拎出来细讲，直接往下看正文
            if (rootInfo.childCount != 0) {
                if (rootInfo == null || TextUtils.isEmpty(rootInfo.className)) {
                    return
                }
                findByID(rootInfo, "扫一扫");
            }
        } catch (e: Exception) {
        }
    }

    private fun findByID(rootInfo: AccessibilityNodeInfo, text: String) {
        if (rootInfo.childCount > 0) {
            for (i in 0 until rootInfo.childCount) {
                val child = rootInfo.getChild(i)
                if (child.text=="扫一扫"){
                    child.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
                findByID(child, text) //递归一直找一层层的全部遍历
            }
        }
    }


}