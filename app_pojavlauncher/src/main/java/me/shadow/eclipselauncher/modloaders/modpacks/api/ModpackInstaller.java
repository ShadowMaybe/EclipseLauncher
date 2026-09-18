package me.shadow.eclipselauncher.modloaders.modpacks.api;

import android.widget.Toast;

import com.kdt.mcgui.ProgressLayout;

import me.shadow.eclipselauncher.R;

import me.shadow.eclipselauncher.Tools;
import me.shadow.eclipselauncher.instances.InstanceInstaller;
import me.shadow.eclipselauncher.instances.Instances;
import me.shadow.eclipselauncher.instances.Instance;
import me.shadow.eclipselauncher.lifecycle.ContextExecutor;
import me.shadow.eclipselauncher.modloaders.modpacks.api.modloader.LoaderInstaller;
import me.shadow.eclipselauncher.modloaders.modpacks.imagecache.ModIconCache;
import me.shadow.eclipselauncher.modloaders.modpacks.models.ModDetail;
import me.shadow.eclipselauncher.progresskeeper.DownloaderProgressWrapper;
import me.shadow.eclipselauncher.utils.DownloadUtils;
import me.shadow.eclipselauncher.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;

public class ModpackInstaller {

    public static LoaderInstaller installModpack(String modpackName, String title, File modpackFile, String icon, InstallFunction installFunction) throws IOException {
        // Build a new minecraft instance, folder first
        LoaderInstaller loaderInstaller;
        Instance instance = Instances.createInstance(i-> i.name = title, modpackName.substring(0, Math.min(16,modpackName.length())));
        try {
            // Install the modpack
            loaderInstaller = installFunction.installModpack(modpackFile, instance.getGameDirectory());

            if(loaderInstaller == null) throw new IOException("Unknown modpack mod loader information");

            if(loaderInstaller.requiresGuiInstallation()) {
                InstanceInstaller instanceInstaller = loaderInstaller.createInstaller();
                if(instanceInstaller == null) throw new IOException("Failed to prepare data for instance installation");
                instance.installer = instanceInstaller;
            } else {
                String versionId = loaderInstaller.installHeadlessly();
                if(versionId == null) throw new IOException("Unknown mod loader version");
                instance.versionId = versionId;
            }
            instance.write();
            ModIconCache.writeInstanceImage(instance, icon);

            Instances.setSelectedInstance(instance);
            if(loaderInstaller.requiresGuiInstallation()) {
                instance.installer.start();
            }
            else ContextExecutor.executeActivity(activity -> Toast.makeText(activity, R.string.modpack_install_toast_success, Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            Instances.removeInstance(instance);
            throw e;
        } finally {
            modpackFile.delete();
            ProgressLayout.clearProgress(ProgressLayout.INSTALL_MODPACK);
        }

        return loaderInstaller;
    }

    public static LoaderInstaller downloadModpack(ModDetail modDetail, int selectedVersion, InstallFunction installFunction) throws IOException {
        String versionUrl = modDetail.versionUrls[selectedVersion];
        String versionHash = modDetail.versionHashes[selectedVersion];
        String modpackName = FileUtils.escapeFileName(modDetail.title.toLowerCase(Locale.ROOT) + " " + modDetail.versionNames[selectedVersion]);
        String name = modDetail.title;
        String icon = modDetail.getIconCacheTag();

        if (versionHash != null) {
            modpackName += "_" + versionHash;
        }

        if (modpackName.length() > 255){
            modpackName = modpackName.substring(0,255);
        }

        File modpackFile = new File(Tools.DIR_CACHE, modpackName + ".cf");

        byte[] downloadBuffer = new byte[8192];
        try {
            DownloadUtils.ensureSha1(modpackFile, versionHash, (Callable<Void>) () -> {
                DownloadUtils.downloadFileMonitored(versionUrl, modpackFile, downloadBuffer,
                        new DownloaderProgressWrapper(R.string.modpack_download_downloading_metadata,
                                ProgressLayout.INSTALL_MODPACK
                        )
                );
                return null;
            });
        } catch (IOException e) {
            modpackFile.delete();
            throw e;
        }

        return installModpack(modpackName, name, modpackFile, icon, installFunction);
    }

    public interface InstallFunction {
        LoaderInstaller installModpack(File modpackFile, File instanceDestination) throws IOException;
    }
}
