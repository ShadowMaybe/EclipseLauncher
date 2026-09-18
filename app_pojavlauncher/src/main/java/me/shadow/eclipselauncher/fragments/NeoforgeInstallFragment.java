package me.shadow.eclipselauncher.fragments;

import android.content.Context;

import androidx.annotation.NonNull;

import me.shadow.eclipselauncher.modloaders.ForgelikeUtils;

import me.shadow.eclipselauncher.R;

public class NeoforgeInstallFragment extends ForgelikeInstallFragment {
    public static final String TAG = "NeoforgeInstallFragment";
    public NeoforgeInstallFragment() {
        super(ForgelikeUtils.NEOFORGE_UTILS, TAG);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public int getTitleText() {
        return R.string.neoforge_dl_select_version;
    }

    @Override
    public int getNoDataMsg() {
        return R.string.neoforge_dl_no_installer;
    }
}
