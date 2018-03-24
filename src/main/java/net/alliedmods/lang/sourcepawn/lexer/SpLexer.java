package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.lexer.FlexAdapter;

import org.jetbrains.annotations.NotNull;

public class SpLexer extends FlexAdapter {
  public SpLexer() {
    super(new _SpLexer(null));
  }

  @NotNull
  public SpPreprocessorLexer createPreprocessorLexer() {
    SpPreprocessorLexer lexer = new SpPreprocessorLexer();
    lexer.addPragmaChangeListener((_SpLexer) getFlex());
    return lexer;
  }
}
