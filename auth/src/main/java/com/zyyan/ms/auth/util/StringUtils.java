package com.zyyan.ms.auth.util;

public class StringUtils {

    private StringUtils() {
    }

    public static String getClassNameFromQualifiedName(String qualifiedName) {
        int index = qualifiedName.lastIndexOf(".");
        try {
            String simplifiedName = qualifiedName.substring(index + 1);
            return simplifiedName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qualifiedName;
    }
}
