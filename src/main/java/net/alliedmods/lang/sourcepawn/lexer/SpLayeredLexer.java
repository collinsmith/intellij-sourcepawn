package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.lexer.LayeredLexer;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.psi.SpElementTypes;

import org.jetbrains.annotations.NotNull;

public class SpLayeredLexer extends LayeredLexer {

  @NotNull
  private final Lexer spLexer;

  @NotNull
  private final Lexer spPreprocessorLexer;

  public SpLayeredLexer() {
    this(new SpPreprocessorLexer());
  }

  public SpLayeredLexer(@NotNull SpPreprocessorLexer preprocessorLexer) {
    super(new SpLexer());
    this.spLexer = (Lexer) getDelegate();
    this.spPreprocessorLexer = preprocessorLexer;
    registerSelfStoppingLayer(spPreprocessorLexer, SpElementTypes.SP_PREPROCESSOR_DIRECTIVES.getTypes(), IElementType.EMPTY_ARRAY);
  }

}
