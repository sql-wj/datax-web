package com.sql.datatx.core.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Kernel32
 */
public interface Kernel32 extends Library {
    Kernel32 INSTANCE = Native.loadLibrary("kernel32", Kernel32.class);

    long GetProcessId(Long hProcess);
}
