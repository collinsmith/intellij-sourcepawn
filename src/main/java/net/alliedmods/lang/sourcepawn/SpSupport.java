package net.alliedmods.lang.sourcepawn;

import com.google.common.collect.ImmutableSet;

import com.intellij.openapi.util.SystemInfo;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class SpSupport {

  @NonNls
  public static final String SP = "sp";

  private static final Set<String> SpScriptExtensions = ImmutableSet.of(SP);

  @NonNls
  public static final String INC = "inc";

  @NonNls
  public static final String INL = "inl";

  private static final Set<String> SpIncludeExtensions = ImmutableSet.of(INC, INL);

  private static final Set<String> SpExtensions = ImmutableSet.<String>builder()
      .addAll(SpScriptExtensions)
      .addAll(SpIncludeExtensions)
      .build();

  @NotNull
  public static Set<String> getScriptExtensions() {
    return SpScriptExtensions;
  }

  @NotNull
  public static Set<String> getIncludeExtensions() {
    return SpIncludeExtensions;
  }

  @NotNull
  public static Set<String> getExtensions() {
    return SpExtensions;
  }

  @NotNull
  private static final String SPCOMP;

  static {
    if (SystemInfo.isWindows) {
      SPCOMP = "scripting/spcomp.exe";
    } else if (SystemInfo.isLinux) {
      SPCOMP = "scripting/spcomp";
    } else if (SystemInfo.isMac) {
      SPCOMP = "scripting/spcomp";
    } else {
      throw new Error("Unsupported platform: " + SystemInfo.OS_NAME);
    }
  }

  @NotNull
  @NonNls
  public static String getCompilerPath() {
    return SPCOMP;
  }

  @NonNls
  private static final String INCLUDES_DIR = "scripting/include";

  @NotNull
  @NonNls
  public static String getIncludesPath() {
    return INCLUDES_DIR;
  }

  @NonNls
  public static final String PLUGIN_TEMPLATE = "SourceMod Plugin.sp";

  @NonNls
  public static final String INCLUDE_TEMPLATE = "SourceMod Include.inc";

  private SpSupport() {}

}
