package me.shadow.eclipselauncher.fragments;

import me.shadow.eclipselauncher.modloaders.FabriclikeUtils;

public class LegacyFabricInstallFragment extends FabriclikeInstallFragment {

    public static final String TAG = "LegacyFabricInstallFragment";
    public LegacyFabricInstallFragment() {
        super(FabriclikeUtils.LEGACY_FABRIC_UTILS, TAG);
    }
}
