package me.shadow.eclipselauncher.modloaders.modpacks.api.modloader;

import me.shadow.eclipselauncher.instances.InstanceInstaller;
import me.shadow.eclipselauncher.modloaders.ForgelikeUtils;

import java.io.IOException;

public class ForgelikeLoaderInstaller implements LoaderInstaller {
    private final ForgelikeUtils mUtils;
    private final String gameVersion;
    private final String loaderVersion;

    public ForgelikeLoaderInstaller(ForgelikeUtils utils, String gameVersion, String loaderVersion) {
        this.mUtils = utils;
        this.gameVersion = gameVersion;
        this.loaderVersion = loaderVersion;
    }

    @Override
    public boolean requiresGuiInstallation() {
        return true;
    }

    @Override
    public InstanceInstaller createInstaller() throws IOException {
        return mUtils.createInstaller(gameVersion, loaderVersion);
    }

    @Override
    public String installHeadlessly() throws IOException {
        throw new RuntimeException("ForgelikeLoaderInstaller only supports GUI installation");
    }
}
