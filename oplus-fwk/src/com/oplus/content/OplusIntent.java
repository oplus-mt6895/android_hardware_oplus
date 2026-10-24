/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.content.Intent;

public class OplusIntent {
    public static void setOplusFlags(Intent intent, int flags) {
        if (intent != null) {
            intent.addFlags(flags);
        }
    }
}
