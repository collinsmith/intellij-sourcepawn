package net.alliedmods.lang.sourcepawn.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.JavaDocTokenType;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlTokenType;

import net.alliedmods.lang.sourcepawn.SpParserDefinition;
import net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes;
import net.alliedmods.lang.sourcepawn.psi.SpElementTypes;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SpSyntaxHighlighter extends SyntaxHighlighterBase {

  private static final Map<IElementType, TextAttributesKey> ourMap1;
  private static final Map<IElementType, TextAttributesKey> ourMap2;
  static {
    ourMap1 = new HashMap<>();
    ourMap2 = new HashMap<>();

    fillMap(ourMap1, SpElementTypes.SP_KEYWORDS, SpHighlightingColors.KEYWORD);
    fillMap(ourMap1, SpElementTypes.SP_OPERATORS, SpHighlightingColors.OPERATION_SIGN);
    fillMap(ourMap1, SpElementTypes.SP_PREPROCESSOR_DIRECTIVES, SpHighlightingColors.PREPROCESSOR);

    //ourMap1.put(SpTokenTypes.INCLUDE_REFERENCE, SpHighlightingColors.STRING);
    //ourMap1.put(SpTokenTypes.DEPRECATION_REASON, SpHighlightingColors.STRING);

    for (IElementType type : JavaDocTokenType.ALL_JAVADOC_TOKENS.getTypes()) {
      ourMap1.put(type, SpHighlightingColors.DOC_COMMENT);
    }

    ourMap1.put(XmlTokenType.XML_DATA_CHARACTERS, SpHighlightingColors.DOC_COMMENT);
    ourMap1.put(XmlTokenType.XML_REAL_WHITE_SPACE, SpHighlightingColors.DOC_COMMENT);
    ourMap1.put(XmlTokenType.TAG_WHITE_SPACE, SpHighlightingColors.DOC_COMMENT);

    ourMap1.put(SpTokenTypes.BOOLEAN_LITERAL, SpHighlightingColors.KEYWORD);
    ourMap1.put(SpTokenTypes.CELL_LITERAL, SpHighlightingColors.NUMBER);
    ourMap1.put(SpTokenTypes.RATIONAL_LITERAL, SpHighlightingColors.NUMBER);
    ourMap1.put(SpTokenTypes.STRING_LITERAL, SpHighlightingColors.STRING);
    ourMap1.put(SpTokenTypes.RAW_STRING_LITERAL, SpHighlightingColors.STRING); // TODO: Special coloring for raw strings?
    ourMap1.put(SpTokenTypes.PACKED_STRING_LITERAL, SpHighlightingColors.STRING); // TODO: Special coloring for packed strings?
    ourMap1.put(SpTokenTypes.PACKED_RAW_STRING_LITERAL, SpHighlightingColors.STRING); // TODO: Special coloring for packed+raw strings?
    ourMap1.put(SpTokenTypes.CHARACTER_LITERAL, SpHighlightingColors.STRING);
    ourMap1.put(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, SpHighlightingColors.VALID_STRING_ESCAPE);
    ourMap1.put(StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN, SpHighlightingColors.INVALID_STRING_ESCAPE);
    ourMap1.put(StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN, SpHighlightingColors.INVALID_STRING_ESCAPE);

    ourMap1.put(SpTokenTypes.LPARENTH, SpHighlightingColors.PARENTHESES);
    ourMap1.put(SpTokenTypes.RPARENTH, SpHighlightingColors.PARENTHESES);

    ourMap1.put(SpTokenTypes.LBRACE, SpHighlightingColors.BRACES);
    ourMap1.put(SpTokenTypes.RBRACE, SpHighlightingColors.BRACES);

    ourMap1.put(SpTokenTypes.LBRACKET, SpHighlightingColors.BRACKETS);
    ourMap1.put(SpTokenTypes.RBRACKET, SpHighlightingColors.BRACKETS);

    ourMap1.put(SpTokenTypes.COMMA, SpHighlightingColors.COMMA);
    ourMap1.put(SpTokenTypes.DOT, SpHighlightingColors.DOT);
    ourMap1.put(SpTokenTypes.SEMICOLON, SpHighlightingColors.SEMICOLON);
    
    ourMap1.put(SpTokenTypes.C_STYLE_COMMENT, SpHighlightingColors.BLOCK_COMMENT);
    ourMap1.put(SpTokenTypes.DOC_COMMENT, SpHighlightingColors.DOC_COMMENT);
    ourMap1.put(SpTokenTypes.EOL_COMMENT, SpHighlightingColors.LINE_COMMENT);
    ourMap1.put(TokenType.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);

    ourMap1.put(JavaDocTokenType.DOC_TAG_NAME, SpHighlightingColors.DOC_COMMENT);
    ourMap2.put(JavaDocTokenType.DOC_TAG_NAME, SpHighlightingColors.DOC_COMMENT_TAG);

    IElementType[] javaDocMarkup = {
        XmlTokenType.XML_START_TAG_START, XmlTokenType.XML_END_TAG_START, XmlTokenType.XML_TAG_END,
        XmlTokenType.XML_EMPTY_ELEMENT_END, XmlTokenType.TAG_WHITE_SPACE, XmlTokenType.XML_TAG_NAME,
        XmlTokenType.XML_NAME, XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN, XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER,
        XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER, XmlTokenType.XML_CHAR_ENTITY_REF, XmlTokenType.XML_EQ
    };

    for (IElementType idx : javaDocMarkup) {
      ourMap1.put(idx, SpHighlightingColors.DOC_COMMENT);
      ourMap2.put(idx, SpHighlightingColors.DOC_COMMENT_MARKUP);
    }
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    final boolean LAYERED = true;
    if (LAYERED) {
      return new SpHighlightingLexer();
    } else {
      return SpParserDefinition.createLexer();
    }
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ourMap1.get(tokenType), ourMap1.get(tokenType));
  }
}
