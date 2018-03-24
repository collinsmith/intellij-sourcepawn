package net.alliedmods.lang.sourcepawn.sdk;

import com.intellij.openapi.projectRoots.AdditionalDataConfigurable;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import net.alliedmods.lang.sourcepawn.SpBundle;
import net.alliedmods.lang.sourcepawn.SpIcons;
import net.alliedmods.lang.sourcepawn.SpSupport;

import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class SpSdkType extends SdkType {

  private static final String ID = "SpSdk";

  @NonNls
  private static final String VERSION_AUTO = "scripting/include/version_auto.inc";

  @NonNls
  private static final String VERSION = "scripting/include/version.inc";

  private static final boolean ADD_INCLUDES_DIRECTLY = true;

  @NotNull
  public static SpSdkType getInstance() {
    return SdkType.findInstance(SpSdkType.class);
  }

  @NotNull
  public static Collection<Sdk> getSpSdks() {
    List<Sdk> sdks = null;
    final SpSdkType spSdkType = SpSdkType.getInstance();
    for (Sdk sdk : ProjectJdkTable.getInstance().getAllJdks()) {
      if (sdk.getSdkType() == spSdkType) {
        if (sdks == null) {
          sdks = new ArrayList<>();
        }

        sdks.add(sdk);
      }
    }

    if (sdks == null) {
      return Collections.emptyList();
    }

    return sdks;
  }

  public SpSdkType() {
    super(ID);
  }

  @Override
  public Icon getIcon() {
    return SpIcons.SDK;
  }

  @NotNull
  @Override
  public Icon getIconForAddAction() {
    return SpIcons.SDK_ADD;
  }

  @Nullable
  @Override
  public String suggestHomePath() {
    return null;
  }

  @Override
  public boolean isValidSdkHome(String path) {
    VirtualFile file = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    return file != null && file.findFileByRelativePath(VERSION) != null;
  }

  @Nullable
  @Override
  public String getVersionString(String sdkHome) {
    VirtualFile versionInfo = LocalFileSystem.getInstance().refreshAndFindFileByPath(
        Paths.get(sdkHome).resolve(VERSION_AUTO).toString());
    if (versionInfo == null) {
      versionInfo = LocalFileSystem.getInstance().refreshAndFindFileByPath(
          Paths.get(sdkHome).resolve(VERSION).toString());
    }

    if (versionInfo == null) {
      return null;
    }

    try (InputStream in = versionInfo.getInputStream();
         InputStreamReader reader = new InputStreamReader(in);
         BufferedReader buffer = new BufferedReader(reader)) {
      String line;
      while ((line = buffer.readLine()) != null) {
        if (line.matches("#define SOURCEMOD_VERSION\\s+\".*\"")) {
          return line
              .replaceFirst("#define SOURCEMOD_VERSION\\s+\"", "")
              .replaceFirst("\"", "");
        }
      }
    } catch (IOException e) {
      return null;
    }

    return null;
  }

  @Override
  public String suggestSdkName(String currentSdkName, String sdkHome) {
    String version = getVersionString(sdkHome);
    if (version != null) {
      Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+");
      Matcher matcher = pattern.matcher(version);
      if (matcher.find()) {
        version = matcher.group();
      }

      return SpBundle.message("sdk.name", version);
    }

    return sdkHome;
  }

  @Nullable
  @Override
  public AdditionalDataConfigurable createAdditionalDataConfigurable(@NotNull SdkModel sdkModel,
                                                                     @NotNull SdkModificator sdkModificator) {
    return null;
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return SpBundle.message("sdk.presentableName");
  }

  @Override
  public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {}

  @Override
  public void setupSdkPaths(@NotNull Sdk sdk) {
    VirtualFile homeDir = sdk.getHomeDirectory();
    if (homeDir == null) {
      return;
    }

    VirtualFile includes = homeDir.findFileByRelativePath(SpSupport.getIncludesPath());
    if (includes != null) {
      SdkModificator sdkModificator = sdk.getSdkModificator();
      if (ADD_INCLUDES_DIRECTLY) {
        for (VirtualFile include : includes.getChildren()) {
          sdkModificator.addRoot(include, OrderRootType.CLASSES);
        }
      } else {
        sdkModificator.addRoot(includes, OrderRootType.CLASSES);
      }

      sdkModificator.commitChanges();
    }
  }

  @NonNls
  public static String getCompilerPath(@NotNull Sdk sdk) {
    VirtualFile sdkHome = sdk.getHomeDirectory();
    if (sdkHome == null) {
      return null;
    }

    VirtualFile compiler = sdkHome.findFileByRelativePath(SpSupport.getCompilerPath());
    if (compiler == null) {
      return null;
    }

    return compiler.getPath();
  }

}
