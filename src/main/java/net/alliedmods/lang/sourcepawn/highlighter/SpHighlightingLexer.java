package net.alliedmods.lang.sourcepawn.highlighter;

import com.intellij.lexer.HtmlHighlightingLexer;
import com.intellij.lexer.LayeredLexer;
import com.intellij.psi.JavaDocTokenType;
import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.SpParserDefinition;
import net.alliedmods.lang.sourcepawn.lexer.SpLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpPreprocessorLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpStringLiteralLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes;

import static org.fest.util.Arrays.array;

public class SpHighlightingLexer extends LayeredLexer {

  public SpHighlightingLexer() {
    super(SpParserDefinition.createLexer());
    SpLexer spLexer = (SpLexer) getDelegate();
    SpPreprocessorLexer preprocessorLexer = spLexer.createPreprocessorLexer();

    SpStringLiteralLexer lexer;

    lexer = new SpStringLiteralLexer('\"', SpTokenTypes.STRING_LITERAL);
    preprocessorLexer.addPragmaChangeListener(lexer);
    registerSelfStoppingLayer(lexer, array(SpTokenTypes.STRING_LITERAL), IElementType.EMPTY_ARRAY);

    lexer = new SpStringLiteralLexer('\"', SpTokenTypes.PACKED_STRING_LITERAL, 2);
    preprocessorLexer.addPragmaChangeListener(lexer);
    registerSelfStoppingLayer(lexer, array(SpTokenTypes.PACKED_STRING_LITERAL), IElementType.EMPTY_ARRAY);

    lexer = new SpStringLiteralLexer('\'', SpTokenTypes.CHARACTER_LITERAL);
    preprocessorLexer.addPragmaChangeListener(lexer);
    registerSelfStoppingLayer(lexer, array(SpTokenTypes.CHARACTER_LITERAL), IElementType.EMPTY_ARRAY);

    LayeredLexer docLexer = new LayeredLexer(SpParserDefinition.createDocLexer());
    HtmlHighlightingLexer htmlLexer = new HtmlHighlightingLexer(null) {{
      setHasNoEmbeddments(true);
    }};
    docLexer.registerLayer(htmlLexer, JavaDocTokenType.DOC_COMMENT_DATA);
    registerSelfStoppingLayer(docLexer, array(SpTokenTypes.DOC_COMMENT), IElementType.EMPTY_ARRAY);

    LayeredLexer preprocessorLayeredLexer = new LayeredLexer(preprocessorLexer) {{
      SpPreprocessorLexer preprocessorLexer = (SpPreprocessorLexer) getDelegate();
      SpStringLiteralLexer lexer;

      lexer = new SpStringLiteralLexer('\"', SpTokenTypes.STRING_LITERAL);
      preprocessorLexer.addPragmaChangeListener(lexer);
      registerSelfStoppingLayer(lexer, array(SpTokenTypes.STRING_LITERAL), IElementType.EMPTY_ARRAY);

      lexer = new SpStringLiteralLexer('\"', SpTokenTypes.PACKED_STRING_LITERAL, 2);
      preprocessorLexer.addPragmaChangeListener(lexer);
      registerSelfStoppingLayer(lexer, array(SpTokenTypes.PACKED_STRING_LITERAL), IElementType.EMPTY_ARRAY);

      lexer = new SpStringLiteralLexer('\'', SpTokenTypes.CHARACTER_LITERAL);
      preprocessorLexer.addPragmaChangeListener(lexer);
      registerSelfStoppingLayer(lexer, array(SpTokenTypes.CHARACTER_LITERAL), IElementType.EMPTY_ARRAY);
    }};
    registerSelfStoppingLayer(preprocessorLayeredLexer, array(SpTokenTypes.PREPROCESSOR_DIRECTIVE), IElementType.EMPTY_ARRAY);
  }

}
