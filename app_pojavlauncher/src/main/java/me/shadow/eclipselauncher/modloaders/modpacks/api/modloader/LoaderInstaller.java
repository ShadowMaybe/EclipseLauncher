package me.shadow.eclipselauncher.modloaders.modpacks.api.modloader;

import me.shadow.eclipselauncher.instances.InstanceInstaller;

import java.io.IOException;

public interface LoaderInstaller {
    boolean requiresGuiInstallation();
    InstanceInstaller createInstaller() throws IOException;
    String installHeadlessly() throws IOException;
}
