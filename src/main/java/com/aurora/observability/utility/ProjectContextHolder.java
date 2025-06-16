package com.aurora.observability.utility;

public class ProjectContextHolder {
    private static final ThreadLocal<String> projectKey = new ThreadLocal<>();

    public static void setProjectKey(String projectId) {
        projectKey.set(projectId);
    }

    public static String getProjectKey() {
        return projectKey.get();
    }

    public static void clear() {
        projectKey.remove();
    }
}
