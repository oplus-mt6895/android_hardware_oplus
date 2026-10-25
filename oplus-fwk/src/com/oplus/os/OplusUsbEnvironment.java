package com.oplus.os;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OplusUsbEnvironment {
    public static final int NONE = -1;
    public static final int INTERNAL = 1;
    public static final int EXTERNAL = 2;
    public static final int OTG = 3;
    public static final String MEDIA_MOUNTED = Environment.MEDIA_MOUNTED;

    public static File getInternalSdDirectory(Context context) {
        return Environment.getExternalStorageDirectory();
    }

    public static String getInternalSdState(Context context) {
        return Environment.getExternalStorageState();
    }

    public static String getInternalPath(Context context) {
        File internal = getInternalSdDirectory(context);
        return internal != null ? internal.getAbsolutePath() : "/storage/emulated/0";
    }

    public static File getExternalSdDirectory(Context context) {
        if (context != null) {
            try {
                StorageManager sm = context.getSystemService(StorageManager.class);
                if (sm != null) {
                    List<StorageVolume> volumes = sm.getStorageVolumes();
                    if (volumes != null) {
                        for (StorageVolume volume : volumes) {
                            if (volume.isRemovable() && !volume.isPrimary()) {
                                return volume.getDirectory();
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public static String getExternalSdState(Context context) {
        if (context != null) {
            try {
                StorageManager sm = context.getSystemService(StorageManager.class);
                if (sm != null) {
                    List<StorageVolume> volumes = sm.getStorageVolumes();
                    if (volumes != null) {
                        for (StorageVolume volume : volumes) {
                            if (volume.isRemovable() && !volume.isPrimary()) {
                                return volume.getState();
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return Environment.MEDIA_REMOVED;
    }

    public static String getExternalPath(Context context) {
        File external = getExternalSdDirectory(context);
        return external != null ? external.getAbsolutePath() : null;
    }

    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    public static String getMultiappSdDirectory() {
        return "/storage/emulated/999";
    }

    public static List<String> getOtgPath(Context context) {
        List<String> list = new ArrayList<>();
        if (context != null) {
            try {
                StorageManager sm = context.getSystemService(StorageManager.class);
                if (sm != null) {
                    List<StorageVolume> volumes = sm.getStorageVolumes();
                    if (volumes != null) {
                        for (StorageVolume volume : volumes) {
                            if (volume.isRemovable() && !volume.isPrimary()) {
                                String desc = volume.getDescription(context);
                                if (desc != null && desc.toLowerCase().contains("otg")) {
                                    File dir = volume.getDirectory();
                                    if (dir != null) {
                                        list.add(dir.getAbsolutePath());
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return list;
    }

    public static int getPathType(Context context, String path) {
        if (path == null || path.isEmpty()) {
            return NONE;
        }

        String internal = getInternalPath(context);
        if (internal != null && path.startsWith(internal)) {
            return INTERNAL;
        }

        String external = getExternalPath(context);
        if (external != null && path.startsWith(external)) {
            return EXTERNAL;
        }

        List<String> otgList = getOtgPath(context);
        if (otgList != null) {
            for (String otg : otgList) {
                if (otg != null && path.startsWith(otg)) {
                    return OTG;
                }
            }
        }

        return NONE;
    }

    public static boolean isExternalSDRemoved(Context context) {
        File dir = getExternalSdDirectory(context);
        if (dir == null) {
            return true;
        }
        return Environment.MEDIA_REMOVED.equals(getExternalSdState(context));
    }

    public static boolean isNestMounted() {
        return false;
    }

    public static boolean isVolumeMounted(Context context, String path) {
        if (path == null) {
            return false;
        }

        File internal = getInternalSdDirectory(context);
        if (internal != null && (path.equals(internal.getPath()) || path.equals(internal.getAbsolutePath()))) {
            return Environment.MEDIA_MOUNTED.equals(getInternalSdState(context));
        }

        File external = getExternalSdDirectory(context);
        if (external != null && (path.equals(external.getPath()) || path.equals(external.getAbsolutePath()))) {
            return Environment.MEDIA_MOUNTED.equals(getExternalSdState(context));
        }

        if (context != null) {
            try {
                StorageManager sm = context.getSystemService(StorageManager.class);
                if (sm != null) {
                    StorageVolume sv = sm.getStorageVolume(new File(path));
                    if (sv != null) {
                        return Environment.MEDIA_MOUNTED.equals(sv.getState());
                    }
                }
            } catch (Exception ignored) {
            }
        }

        if (internal != null && path.startsWith(internal.getAbsolutePath())) {
            return Environment.MEDIA_MOUNTED.equals(getInternalSdState(context));
        }

        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
