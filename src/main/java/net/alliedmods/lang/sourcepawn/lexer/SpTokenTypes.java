package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.psi.JavaTokenType;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.JavaDocElementType;
import com.intellij.psi.tree.IElementType;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
public interface SpTokenTypes extends TokenType {

  IElementType SEMICOLON = new SpTokenType("SEMICOLON");
  IElementType SEMICOLON_SYNTHETIC = new SpTokenType("SEMICOLON_SYNTHETIC");

  IElementType IDENTIFIER = new SpTokenType("-identifier-");
  IElementType C_STYLE_COMMENT = JavaTokenType.C_STYLE_COMMENT;
  IElementType EOL_COMMENT = JavaTokenType.END_OF_LINE_COMMENT;
  IElementType DOC_COMMENT = JavaDocElementType.DOC_COMMENT;

  IElementType BOOLEAN_LITERAL = new SpTokenType("BOOLEAN_LITERAL");
  IElementType CELL_LITERAL = new SpTokenType("CELL_LITERAL");
  IElementType RATIONAL_LITERAL = new SpTokenType("RATIONAL_LITERAL");
  IElementType CHARACTER_LITERAL = new SpTokenType("CHARACTER_LITERAL");
  IElementType STRING_LITERAL = new SpTokenType("STRING_LITERAL");
  IElementType RAW_STRING_LITERAL = new SpTokenType("RAW_STRING_LITERAL");
  IElementType PACKED_STRING_LITERAL = new SpTokenType("PACKED_STRING_LITERAL");
  IElementType PACKED_RAW_STRING_LITERAL = new SpTokenType("PACKED_RAW_STRING_LITERAL");

  IElementType AQUIRE_KEYWORD = new SpTokenType("AQUIRE_KEYWORD");
  IElementType AS_KEYWORD = new SpTokenType("AS_KEYWORD");
  IElementType ASSERT_KEYWORD = new SpTokenType("ASSERT_KEYWORD");
  IElementType BREAK_KEYWORD = new SpTokenType("BREAK_KEYWORD");
  IElementType BUILTIN_KEYWORD = new SpTokenType("BUILTIN_KEYWORD");
  IElementType CATCH_KEYWORD = new SpTokenType("CATCH_KEYWORD");
  IElementType CASE_KEYWORD = new SpTokenType("CASE_KEYWORD");
  IElementType CAST_TO_KEYWORD = new SpTokenType("CAST_TO_KEYWORD");
  IElementType CELLSOF_KEYWORD = new SpTokenType("CELLSOF_KEYWORD");
  IElementType CHAR_KEYWORD = new SpTokenType("CHAR_KEYWORD");
  IElementType CONST_KEYWORD = new SpTokenType("CONST_KEYWORD");
  IElementType CONTINUE_KEYWORD = new SpTokenType("CONTINUE_KEYWORD");
  IElementType DECL_KEYWORD = new SpTokenType("DECL_KEYWORD");
  IElementType DEFAULT_KEYWORD = new SpTokenType("DEFAULT_KEYWORD");
  IElementType DEFINED_KEYWORD = new SpTokenType("DEFINED_KEYWORD");
  IElementType DELETE_KEYWORD = new SpTokenType("DELETE_KEYWORD");
  IElementType DO_KEYWORD = new SpTokenType("DO_KEYWORD");
  IElementType DOUBLE_KEYWORD = new SpTokenType("DOUBLE_KEYWORD");
  IElementType ELSE_KEYWORD = new SpTokenType("ELSE_KEYWORD");
  IElementType ENUM_KEYWORD = new SpTokenType("ENUM_KEYWORD");
  IElementType EXIT_KEYWORD = new SpTokenType("EXIT_KEYWORD");
  IElementType EXPLICIT_KEYWORD = new SpTokenType("EXPLICIT_KEYWORD");
  IElementType FINALLY_KEYWORD = new SpTokenType("FINALLY_KEYWORD");
  IElementType FOR_KEYWORD = new SpTokenType("FOR_KEYWORD");
  IElementType FOREACH_KEYWORD = new SpTokenType("FOREACH_KEYWORD");
  IElementType FORWARD_KEYWORD = new SpTokenType("FORWARD_KEYWORD");
  IElementType FUNCENUM_KEYWORD = new SpTokenType("FUNCENUM_KEYWORD");
  IElementType FUNCTAG_KEYWORD = new SpTokenType("FUNCTAG_KEYWORD");
  IElementType FUNCTION_KEYWORD = new SpTokenType("FUNCTION_KEYWORD");
  IElementType GOTO_KEYWORD = new SpTokenType("GOTO_KEYWORD");
  IElementType IF_KEYWORD = new SpTokenType("IF_KEYWORD");
  IElementType IMPLICIT_KEYWORD = new SpTokenType("IMPLICIT_KEYWORD");
  IElementType IMPORT_KEYWORD = new SpTokenType("IMPORT_KEYWORD");
  IElementType IN_KEYWORD = new SpTokenType("IN_KEYWORD");
  IElementType INT_KEYWORD = new SpTokenType("INT_KEYWORD");
  IElementType INT8_KEYWORD = new SpTokenType("INT8_KEYWORD");
  IElementType INT16_KEYWORD = new SpTokenType("INT16_KEYWORD");
  IElementType INT32_KEYWORD = new SpTokenType("INT32_KEYWORD");
  IElementType INT64_KEYWORD = new SpTokenType("INT64_KEYWORD");
  IElementType INTERFACE_KEYWORD = new SpTokenType("INTERFACE_KEYWORD");
  IElementType INTN_KEYWORD = new SpTokenType("INTN_KEYWORD");
  IElementType LET_KEYWORD = new SpTokenType("LET_KEYWORD");
  IElementType METHODMAP_KEYWORD = new SpTokenType("METHODMAP_KEYWORD");
  IElementType NAMESPACE_KEYWORD = new SpTokenType("NAMESPACE_KEYWORD");
  IElementType NATIVE_KEYWORD = new SpTokenType("NATIVE_KEYWORD");
  IElementType NEW_KEYWORD = new SpTokenType("NEW_KEYWORD");
  IElementType NULL_KEYWORD = new SpTokenType("NULL_KEYWORD");
  IElementType NULLABLE_KEYWORD = new SpTokenType("NULLABLE_KEYWORD");
  IElementType OBJECT_KEYWORD = new SpTokenType("OBJECT_KEYWORD");
  IElementType OPERATOR_KEYWORD = new SpTokenType("OPERATOR_KEYWORD");
  IElementType PACKAGE_KEYWORD = new SpTokenType("PACKAGE_KEYWORD");
  IElementType PRIVATE_KEYWORD = new SpTokenType("PRIVATE_KEYWORD");
  IElementType PROTECTED_KEYWORD = new SpTokenType("PROTECTED_KEYWORD");
  IElementType PUBLIC_KEYWORD = new SpTokenType("PUBLIC_KEYWORD");
  IElementType READONLY_KEYWORD = new SpTokenType("READONLY_KEYWORD");
  IElementType RETURN_KEYWORD = new SpTokenType("RETURN_KEYWORD");
  IElementType SEALED_KEYWORD = new SpTokenType("SEALED_KEYWORD");
  IElementType SIZEOF_KEYWORD = new SpTokenType("SIZEOF_KEYWORD");
  IElementType SLEEP_KEYWORD = new SpTokenType("SLEEP_KEYWORD");
  IElementType STATIC_KEYWORD = new SpTokenType("STATIC_KEYWORD");
  IElementType STOCK_KEYWORD = new SpTokenType("STOCK_KEYWORD");
  IElementType STRUCT_KEYWORD = new SpTokenType("STRUCT_KEYWORD");
  IElementType SWITCH_KEYWORD = new SpTokenType("SWITCH_KEYWORD");
  IElementType TAGOF_KEYWORD = new SpTokenType("TAGOF_KEYWORD");
  IElementType THIS_KEYWORD = new SpTokenType("THIS_KEYWORD");
  IElementType THROW_KEYWORD = new SpTokenType("THROW_KEYWORD");
  IElementType TRY_KEYWORD = new SpTokenType("TRY_KEYWORD");
  IElementType TYPEDEF_KEYWORD = new SpTokenType("TYPEDEF_KEYWORD");
  IElementType TYPEOF_KEYWORD = new SpTokenType("TYPEOF_KEYWORD");
  IElementType TYPESET_KEYWORD = new SpTokenType("TYPESET_KEYWORD");
  IElementType UINT8_KEYWORD = new SpTokenType("UINT8_KEYWORD");
  IElementType UINT16_KEYWORD = new SpTokenType("UINT16_KEYWORD");
  IElementType UINT32_KEYWORD = new SpTokenType("UINT32_KEYWORD");
  IElementType UINT64_KEYWORD = new SpTokenType("UINT64_KEYWORD");
  IElementType UINTN_KEYWORD = new SpTokenType("UINTN_KEYWORD");
  IElementType UNION_KEYWORD = new SpTokenType("UNION_KEYWORD");
  IElementType USING_KEYWORD = new SpTokenType("USING_KEYWORD");
  IElementType VAR_KEYWORD = new SpTokenType("VAR_KEYWORD");
  IElementType VARIANT_KEYWORD = new SpTokenType("VARIANT_KEYWORD");
  IElementType VIEW_AS_KEYWORD = new SpTokenType("VIEW_AS_KEYWORD");
  IElementType VIRTUAL_KEYWORD = new SpTokenType("VIRTUAL_KEYWORD");
  IElementType VOID_KEYWORD = new SpTokenType("VOID_KEYWORD");
  IElementType VOLATILE_KEYWORD = new SpTokenType("VOLATILE_KEYWORD");
  IElementType WHILE_KEYWORD = new SpTokenType("WHILE_KEYWORD");
  IElementType WITH_KEYWORD = new SpTokenType("WITH_KEYWORD");

  IElementType LPARENTH = new SpTokenType("LPARENTH");
  IElementType RPARENTH = new SpTokenType("RPARENTH");
  IElementType LBRACE = new SpTokenType("LBRACE");
  IElementType RBRACE = new SpTokenType("RBRACE");
  IElementType LBRACKET = new SpTokenType("LBRACKET");
  IElementType RBRACKET = new SpTokenType("RBRACKET");
  IElementType COMMA = new SpTokenType("COMMA");
  IElementType DOT = new SpTokenType("DOT");
  IElementType DOTDOTDOT = new SpTokenType("DOTDOTDOT");
  IElementType AT = new SpTokenType("AT");

  IElementType EQ = new SpTokenType("EQ");
  IElementType GT = new SpTokenType("GT");
  IElementType LT = new SpTokenType("LT");
  IElementType EXCL = new SpTokenType("EXCL");
  IElementType TILDE = new SpTokenType("TILDE");
  IElementType QUEST = new SpTokenType("QUEST");
  IElementType COLON = new SpTokenType("COLON");
  IElementType PLUS = new SpTokenType("PLUS");
  IElementType MINUS = new SpTokenType("MINUS");
  IElementType ASTERISK = new SpTokenType("ASTERISK");
  IElementType DIV = new SpTokenType("DIV");
  IElementType AND = new SpTokenType("AND");
  IElementType OR = new SpTokenType("OR");
  IElementType XOR = new SpTokenType("XOR");
  IElementType PERC = new SpTokenType("PERC");

  IElementType EQEQ = new SpTokenType("EQEQ");
  IElementType LTEQ = new SpTokenType("LTEQ");
  IElementType GTEQ = new SpTokenType("GTEQ");
  IElementType EXCLEQ = new SpTokenType("EXCLEQ");
  IElementType ANDAND = new SpTokenType("ANDAND");
  IElementType OROR = new SpTokenType("OROR");
  IElementType PLUSPLUS = new SpTokenType("PLUSPLUS");
  IElementType MINUSMINUS = new SpTokenType("MINUSMINUS");
  IElementType LTLT = new SpTokenType("LTLT");
  IElementType GTGT = new SpTokenType("GTGT");
  IElementType GTGTGT = new SpTokenType("GTGTGT");
  IElementType PLUSEQ = new SpTokenType("PLUSEQ");
  IElementType MINUSEQ = new SpTokenType("MINUSEQ");
  IElementType ASTERISKEQ = new SpTokenType("ASTERISKEQ");
  IElementType DIVEQ = new SpTokenType("DIVEQ");
  IElementType ANDEQ = new SpTokenType("ANDEQ");
  IElementType OREQ = new SpTokenType("OREQ");
  IElementType XOREQ = new SpTokenType("XOREQ");
  IElementType PERCEQ = new SpTokenType("PERCEQ");
  IElementType LTLTEQ = new SpTokenType("LTLTEQ");
  IElementType GTGTEQ = new SpTokenType("GTGTEQ");
  IElementType GTGTGTEQ = new SpTokenType("GTGTGTEQ");

  // Operators specific to SourcePawn (the above are pretty generic to all high-level languages)
  IElementType UNDERSCORE = new SpTokenType("UNDERSCORE");
  IElementType DOTDOT = new SpTokenType("DOTDOT");
  IElementType COLONCOLON = new SpTokenType("COLONCOLON");

  IElementType PREPROCESSOR_DIRECTIVE = new SpTokenType("PREPROCESSOR_DIRECTIVE");

  IElementType HASH = new SpPreprocessorTokenType("HASH");
  IElementType ESCAPING_SLASH = new SpPreprocessorTokenType("ESCAPING_SLASH");

  IElementType ASSERT_DIRECTIVE = new SpPreprocessorTokenType("ASSERT_DIRECTIVE");
  IElementType DEFINE_DIRECTIVE = new SpPreprocessorTokenType("DEFINE_DIRECTIVE");
  IElementType ELSE_DIRECTIVE = new SpPreprocessorTokenType("ELSE_DIRECTIVE");
  IElementType ELSEIF_DIRECTIVE = new SpPreprocessorTokenType("ELSEIF_DIRECTIVE");
  IElementType ENDIF_DIRECTIVE = new SpPreprocessorTokenType("ENDIF_DIRECTIVE");
  IElementType ENDINPUT_DIRECTIVE = new SpPreprocessorTokenType("ENDINPUT_DIRECTIVE");
  IElementType ENDSCRIPT_DIRECTIVE = new SpPreprocessorTokenType("ENDSCRIPT_DIRECTIVE");
  IElementType ERROR_DIRECTIVE = new SpPreprocessorTokenType("ERROR_DIRECTIVE");
  IElementType FILE_DIRECTIVE = new SpPreprocessorTokenType("FILE_DIRECTIVE");
  IElementType IF_DIRECTIVE = new SpPreprocessorTokenType("IF_DIRECTIVE");
  IElementType INCLUDE_DIRECTIVE = new SpPreprocessorTokenType("INCLUDE_DIRECTIVE");
  IElementType LINE_DIRECTIVE = new SpPreprocessorTokenType("LINE_DIRECTIVE");
  IElementType PRAGMA_DIRECTIVE = new SpPreprocessorTokenType("PRAGMA_DIRECTIVE");
  IElementType TRYINCLUDE_DIRECTIVE = new SpPreprocessorTokenType("TRYINCLUDE_DIRECTIVE");
  IElementType UNDEF_DIRECTIVE = new SpPreprocessorTokenType("UNDEF_DIRECTIVE");
  IElementType WARNING_DIRECTIVE = new SpPreprocessorTokenType("WARNING_DIRECTIVE");

  IElementType MACRO_PARAMETER = new SpPreprocessorTokenType("MACRO_PARAMETER");
  IElementType MACRO_STRINGIZED_PARAMETER = new SpPreprocessorTokenType("MACRO_STRINGIZED_PARAMETER");

//  TokenSet PREPROCESSOR_DIRECTIVE = TokenSet.create(
//      ASSERT_DIRECTIVE, DEFINE_DIRECTIVE, ELSE_DIRECTIVE, ELSEIF_DIRECTIVE, ENDIF_DIRECTIVE,
//      ENDINPUT_DIRECTIVE, ENDSCRIPT_DIRECTIVE, ERROR_DIRECTIVE, FILE_DIRECTIVE, IF_DIRECTIVE,
//      INCLUDE_DIRECTIVE, LINE_DIRECTIVE, PRAGMA_DIRECTIVE, TRYINCLUDE_DIRECTIVE, UNDEF_DIRECTIVE,
//      WARNING_DIRECTIVE);

}
