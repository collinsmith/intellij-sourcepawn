package net.alliedmods.lang.sourcepawn;

import com.intellij.CommonBundle;
import com.intellij.reference.SoftReference;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.util.ResourceBundle;

public class SpBundle {

  private static Reference<ResourceBundle> spBundle;

  @NonNls
  private static final String BUNDLE = "i18n";

  public static String message(@NotNull @NonNls @PropertyKey(resourceBundle = BUNDLE) String key,
                               @NotNull @NonNls Object... params) {
    return CommonBundle.message(getBundle(), key, params);
  }

  // Method copied from IntelliJ
  @NotNull
  private static ResourceBundle getBundle() {
    ResourceBundle bundle = SoftReference.dereference(spBundle);
    if (bundle == null) {
      bundle = ResourceBundle.getBundle(BUNDLE);
      spBundle = new java.lang.ref.SoftReference<>(bundle);
    }

    return bundle;
  }

  private SpBundle() {}

}
