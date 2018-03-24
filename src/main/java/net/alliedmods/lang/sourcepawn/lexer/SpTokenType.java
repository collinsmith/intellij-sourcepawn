package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.SpLanguage;

import org.jetbrains.annotations.NonNls;

public class SpTokenType extends IElementType {
  @NonNls private final String debugName;
  private final boolean leftBound;

  public SpTokenType(@NonNls String debugName) {
    this(debugName, true);
  }

  public SpTokenType(@NonNls String debugName, boolean leftBound) {
    super(debugName, SpLanguage.INSTANCE);
    this.debugName = debugName;
    this.leftBound = leftBound;
  }

  @Override
  public boolean isLeftBound() {
    return leftBound;
  }

  @NonNls
  public String getDebugName() {
    return debugName;
  }

}
