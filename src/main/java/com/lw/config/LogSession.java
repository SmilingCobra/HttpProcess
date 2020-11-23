package com.lw.config;

public class LogSession {
    public static ThreadLocal<LogContext> logSession =new InheritableThreadLocal<>();
}
