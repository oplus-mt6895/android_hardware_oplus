/*
 * Copyright (C) 2024-2026 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oplus.util;

import android.content.Context;

public class OplusPlatformLevelUtils {
    private static volatile OplusPlatformLevelUtils sInstance;

    private OplusPlatformLevelUtils() {}

    public static OplusPlatformLevelUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusPlatformLevelUtils.class) {
                if (sInstance == null) {
                    sInstance = new OplusPlatformLevelUtils();
                }
            }
        }
        return sInstance;
    }

    public int getPlatformLevel(int type) {
        // shortcut: return high performance level (3) for Dimensity 8100
        return 3;
    }

    public int getPlatformAnimationLevel() {
        return 3;
    }

    public int getPlatformGaussianLevel() {
        return 3;
    }
}
