/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.text.TextUtils;

import java.util.List;

public class OplusFeatureConfigManager implements IOplusFeatureConfigManager {
    public static OplusFeatureConfigManager sInstance = null;

    public interface OnFeatureActionObserver {
        default void onFeaturesActionUpdate(String action, String feature, IOplusFeatureConfigManager.FeatureID featureID) {}
    }

    public interface OnFeatureObserver {
        default void onFeatureUpdate(List<String> features) {}
    }

    public static OplusFeatureConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusFeatureConfigManager();
        }
        return sInstance;
    }

    public boolean hasFeature(String featureName) {
        return false;
    }

    public boolean hasFeature(String featureName, int flag) {
        return false;
    }

    public boolean isPermit(String featureName) {
        return true;
    }

    public boolean registerFeatureActionObserver(OnFeatureActionObserver observer) {
        return true;
    }

    public boolean unregisterFeatureActionObserver(OnFeatureActionObserver observer) {
        return true;
    }

    public boolean registerFeatureObserver(List<String> features, OnFeatureObserver observer) {
        return true;
    }

    public boolean unregisterFeatureObserver(OnFeatureObserver observer) {
        return true;
    }
}
