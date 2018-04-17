package net.alliedmods.lang.sourcepawn.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.ICompositeElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.ReflectionUtil;

import net.alliedmods.lang.sourcepawn.SpLanguage;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes.*;

public interface SpElementTypes {

  @NotNull
  static TokenSet tokenSetOf(@NotNull IElementType... types) {
    return TokenSet.create(types);
  }

  TokenSet SP_WHITESPACES = tokenSetOf(WHITE_SPACE);

  TokenSet SP_DOC_COMMENTS = tokenSetOf(DOC_COMMENT);

  TokenSet SP_COMMENTS = TokenSet.orSet(tokenSetOf(C_STYLE_COMMENT, EOL_COMMENT), SP_DOC_COMMENTS);

  TokenSet SP_STRING_LITERALS = tokenSetOf(
      CHARACTER_LITERAL, STRING_LITERAL, RAW_STRING_LITERAL, PACKED_STRING_LITERAL,
      PACKED_RAW_STRING_LITERAL);

  TokenSet SP_COMMENT_OR_WHITESPACE = TokenSet.orSet(SP_COMMENTS, SP_WHITESPACES);

  TokenSet SP_KEYWORDS = tokenSetOf(
      AQUIRE_KEYWORD, AS_KEYWORD, ASSERT_KEYWORD, BREAK_KEYWORD, BUILTIN_KEYWORD, CATCH_KEYWORD,
      CASE_KEYWORD, CAST_TO_KEYWORD, CELLSOF_KEYWORD, CHAR_KEYWORD, CONST_KEYWORD, CONTINUE_KEYWORD,
      DECL_KEYWORD, DEFAULT_KEYWORD, DEFINED_KEYWORD, DELETE_KEYWORD, DO_KEYWORD, DOUBLE_KEYWORD,
      ELSE_KEYWORD, ENUM_KEYWORD, EXIT_KEYWORD, EXPLICIT_KEYWORD, FINALLY_KEYWORD, FOR_KEYWORD,
      FOREACH_KEYWORD, FORWARD_KEYWORD, FUNCENUM_KEYWORD, FUNCTAG_KEYWORD, FUNCTION_KEYWORD,
      GOTO_KEYWORD, IF_KEYWORD, IMPLICIT_KEYWORD, IMPORT_KEYWORD, IN_KEYWORD, INT_KEYWORD,
      INT8_KEYWORD, INT16_KEYWORD, INT32_KEYWORD, INT64_KEYWORD, INTERFACE_KEYWORD, INTN_KEYWORD,
      LET_KEYWORD, METHODMAP_KEYWORD, NAMESPACE_KEYWORD, NATIVE_KEYWORD, NEW_KEYWORD, NULL_KEYWORD,
      NULLABLE_KEYWORD, OBJECT_KEYWORD, OPERATOR_KEYWORD, PACKAGE_KEYWORD, PRIVATE_KEYWORD,
      PROTECTED_KEYWORD, PUBLIC_KEYWORD, READONLY_KEYWORD, RETURN_KEYWORD, SEALED_KEYWORD,
      SIZEOF_KEYWORD, SLEEP_KEYWORD, STATIC_KEYWORD, STOCK_KEYWORD, STRUCT_KEYWORD, SWITCH_KEYWORD,
      TAGOF_KEYWORD, THIS_KEYWORD, THROW_KEYWORD, TRY_KEYWORD, TYPEDEF_KEYWORD, TYPEOF_KEYWORD,
      TYPESET_KEYWORD, UINT8_KEYWORD, UINT16_KEYWORD, UINT32_KEYWORD, UINT64_KEYWORD, UINTN_KEYWORD,
      UNION_KEYWORD, USING_KEYWORD, VAR_KEYWORD, VARIANT_KEYWORD, VIEW_AS_KEYWORD, VIRTUAL_KEYWORD,
      VOID_KEYWORD, VOLATILE_KEYWORD, WHILE_KEYWORD, WITH_KEYWORD);

  TokenSet SP_OPERATORS = tokenSetOf(
      AND, ANDEQ, ASTERISK, ASTERISKEQ, COLON, COLONCOLON, COMMA, DIV, DIVEQ, DOT, DOTDOT,
      DOTDOTDOT, EQ, EQEQ, EXCL, EXCLEQ, GT, LT, MINUS, MINUSEQ, OR, OREQ, PERC, PERCEQ, PLUS,
      PLUSEQ, SEMICOLON, TILDE, XOR, XOREQ, QUEST, AT, GTGTGT, GTGTGTEQ, GTGT, GTGTEQ, GTEQ, LTLTEQ,
      LTLT, LTEQ, OROR, ANDAND, PLUSPLUS, MINUSMINUS, UNDERSCORE);

  TokenSet SP_BINARY_OPS = tokenSetOf(
      AND, ANDEQ, ANDAND,
      ASTERISK, ASTERISKEQ,
      DIV, DIVEQ,
      EQ, EQEQ, EXCLEQ,
      GT, GTGT, GTEQ, GTGTEQ, GTGTGT, GTGTGTEQ,
      LT, LTLT, LTEQ, LTLTEQ,
      MINUS, MINUSEQ,
      OR, OREQ, OROR,
      PERC, PERCEQ,
      PLUS, PLUSEQ,
      XOR, XOREQ);

  TokenSet SP_PREPROCESSOR_DIRECTIVES = tokenSetOf(
      PREPROCESSOR_DIRECTIVE,
      HASH, ESCAPING_SLASH,
      ASSERT_DIRECTIVE, DEFINE_DIRECTIVE, ELSE_DIRECTIVE, ELSEIF_DIRECTIVE, ENDIF_DIRECTIVE,
      ENDINPUT_DIRECTIVE, ENDSCRIPT_DIRECTIVE, ERROR_DIRECTIVE, WARNING_DIRECTIVE, FILE_DIRECTIVE,
      IF_DIRECTIVE, INCLUDE_DIRECTIVE, LINE_DIRECTIVE, PRAGMA_DIRECTIVE, TRYINCLUDE_DIRECTIVE,
      UNDEF_DIRECTIVE);

  class Factory {
    public static PsiElement createElement(final ASTNode node) {
      return new ASTWrapperPsiElement(node);
      //final IElementType type = node.getElementType();
      //throw new AssertionError("Unknown element type: " + type);
    }
  }

  class SpIElementType extends IElementType {
    public SpIElementType(@NotNull @NonNls String debugName) {
      super(debugName, SpLanguage.INSTANCE);
    }
  }

  class SpICompositeElementType extends SpIElementType implements ICompositeElementType {
    private final Constructor<? extends ASTNode> constructor;

    public SpICompositeElementType(@NotNull @NonNls String debugName, Class<? extends ASTNode> nodeClass) {
      super(debugName);
      this.constructor = ReflectionUtil.getDefaultConstructor(nodeClass);
    }

    @NotNull
    @Override
    public ASTNode createCompositeNode() {
      return ReflectionUtil.createInstance(constructor);
    }
  }

}
