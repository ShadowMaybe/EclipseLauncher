package me.shadow.eclipselauncher.tasks;

import me.shadow.eclipselauncher.JVersionList;
import me.shadow.eclipselauncher.extra.ExtraConstants;
import me.shadow.eclipselauncher.extra.ExtraCore;
import me.shadow.eclipselauncher.instances.Instance;

import java.io.File;

public class MoJsonExtras {
    public static String normalizeVersionId(String versionString) {
        JVersionList versionList = (JVersionList) ExtraCore.getValue(ExtraConstants.RELEASE_TABLE);
        if(versionList == null || versionList.versions == null) return versionString;
        if(Instance.VERSION_LATEST_RELEASE.equals(versionString)) versionString = versionList.latest.get("release");
        if(Instance.VERSION_LATEST_SNAPSHOT.equals(versionString)) versionString = versionList.latest.get("snapshot");
        return versionString;
    }

    public static JVersionList.Version getListedVersion(String normalizedVersionString) {
        JVersionList versionList = (JVersionList) ExtraCore.getValue(ExtraConstants.RELEASE_TABLE);
        if(versionList == null || versionList.versions == null) return null; // can't have listed versions if there's no list
        for(JVersionList.Version version : versionList.versions) {
            if(version.id.equals(normalizedVersionString)) return version;
        }
        return null;
    }

    public interface DoneListener{
        void onDownloadDone(File[] classpath);
        void onDownloadFailed(Throwable throwable);
    }
}
