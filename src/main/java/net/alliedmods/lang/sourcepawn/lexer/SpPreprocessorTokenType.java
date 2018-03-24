package net.alliedmods.lang.sourcepawn.lexer;

import org.jetbrains.annotations.NonNls;

public class SpPreprocessorTokenType extends SpTokenType {

  public SpPreprocessorTokenType(@NonNls String debugName) {
    super(debugName);
  }

  public SpPreprocessorTokenType(@NonNls String debugName, boolean leftBound) {
    super(debugName, leftBound);
  }

}
