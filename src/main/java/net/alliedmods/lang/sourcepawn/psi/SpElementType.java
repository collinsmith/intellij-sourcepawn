package net.alliedmods.lang.sourcepawn.psi;

import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.SpLanguage;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SpElementType extends IElementType {

  public SpElementType(@NotNull @NonNls String debugName) {
    super(debugName, SpLanguage.INSTANCE);
  }

}
