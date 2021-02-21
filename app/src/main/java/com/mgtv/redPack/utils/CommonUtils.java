package com.mgtv.redPack.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 文件描述:
 * 作 者: 向庚平
 * 邮 箱: gengping@mgtv.com
 * 创建时间: 2021/1/12.
 */
public class CommonUtils {
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    static {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = ContextProvider.getApplicationContext();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(displayMetrics);
        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HEIGHT = displayMetrics.heightPixels;
    }

    private static Rect mTmp = new Rect();
    private static Rect mHeadIcon = new Rect();

    /**
     * 是否是自己发送的红包
     * @return
     */
    public static boolean isSelfSend(AccessibilityNodeInfo node) {
        if (node == null) {
            return false;
        }
        mTmp.setEmpty();
        node.getBoundsInScreen(mTmp);

        AccessibilityNodeInfo moneyNode = node.getParent();
        if (moneyNode == null) {
            return false;
        }
        AccessibilityNodeInfo messageNode = moneyNode.getParent();
        mHeadIcon.setEmpty();
        int count = messageNode.getChildCount();
        for (int i = 0; i < count; i++) {
            AccessibilityNodeInfo thisNode = messageNode.getChild(i);
            if ("android.widget.ImageView".equals(thisNode.getClassName())) {
                CharSequence contentDescription = thisNode.getContentDescription();
                if (contentDescription != null && contentDescription.toString().contains("头像")) {
                    thisNode.getBoundsInScreen(mHeadIcon);
                }
            }
        }
        return mHeadIcon.left > mTmp.left;
    }

    public static String getNodeInfo(AccessibilityNodeInfo node) {
        if (node == null) {
            return "";
        }
        AccessibilityNodeInfo moneyNode = node.getParent();
        if (moneyNode == null) {
            return "";
        }
        AccessibilityNodeInfo messageNode = moneyNode.getParent();
        if (messageNode == null) {
            return "";
        }
        int count = messageNode.getChildCount();
        String[] result = { "unknownSender", "unknownTime" };
        for (int i = 0; i < count; i++) {
            AccessibilityNodeInfo thisNode = messageNode.getChild(i);
            if ("android.widget.ImageView".equals(thisNode.getClassName()) && "unknownSender".equals(result[0])) {
                CharSequence contentDescription = thisNode.getContentDescription();
                if (contentDescription != null)
                    result[0] = contentDescription.toString().replaceAll("头像$", "");
            } else if ("android.widget.TextView".equals(thisNode.getClassName()) && "unknownTime".equals(result[1])) {
                CharSequence thisNodeText = thisNode.getText();
                if (thisNodeText != null)
                    result[1] = thisNodeText.toString();
            }
        }
        String signature = "";
        for (String str : result) {
            if (str == null)
                return null;
            signature += str + "|";
        }
        return signature.substring(0, signature.length() - 1);
    }
}
