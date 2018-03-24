package net.alliedmods.lang.sourcepawn.lexer;

import java.util.concurrent.CopyOnWriteArrayList;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;

import static net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes.*;

%%

%{
  private int sc_ctrlchar = '\\';
  private boolean sc_needsemicolon = false;

  private CopyOnWriteArrayList<PragmaChangeListener> listeners = new CopyOnWriteArrayList<>();

  /** Indicates whether or not lexed string is packed. */
  private boolean packed = false;

  private static String zzToPrintable(CharSequence str) {
    return zzToPrintable(str.toString());
  }

  private void setPragma(/*@NotNull @NonNls*/ String pragma, Object value) {
    for (PragmaChangeListener l : listeners) {
      l.onPragmaChanged(pragma, value);
    }
  }

  public void addPragmaChangeListener(@NotNull PragmaChangeListener l) {
    listeners.add(l);
    l.onPragmaChanged("ctrlchar", sc_ctrlchar);
    l.onPragmaChanged("semicolon", sc_needsemicolon);
  }
%}

%unicode
%class _SpPreprocessorLexer
%implements FlexLexer
%function advance
%type IElementType
//%debug

WS = [\ \t\f]
NL = \r|\n|\r\n
CONT = "\\" {WS}* {NL} {WS}*

//ALPHA = [_@a-zA-Z] // Embedded into {IDENTIFIER} declaration
ALPHA_NUM = [_@a-zA-Z0-9]
IDENTIFIER = ([_@]{ALPHA_NUM}+) | ([a-zA-Z]{ALPHA_NUM}*)

C_STYLE_COMMENT = ("/*"[^"*"]{COMMENT_TAIL})|"/*"
DOC_COMMENT = "/*""*"+("/"|([^"/""*"]{COMMENT_TAIL}))?
COMMENT_TAIL = ([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT = "/""/"[^\r\n]*

BINARY_DIGIT = [01]
OCTAL_DIGIT = [0-7]
DECIMAL_DIGIT = [0-9]
HEXADECIMAL_DIGIT = [0-9a-fA-F]

BINARY_LITERAL = 0b ( _ | {BINARY_DIGIT} )*
OCTAL_LITERAL = 0o ( _ | {OCTAL_DIGIT} )*
DECIMAL_LITERAL = {DECIMAL_DIGIT} ( _ | {DECIMAL_DIGIT} )*
HEXADECIMAL_LITERAL = 0x ( _ | {HEXADECIMAL_DIGIT} )*

RATIONAL_LITERAL = {DECIMAL_DIGIT} "." {DECIMAL_DIGIT} {RATIONAL_EXPONENT}?
RATIONAL_EXPONENT = e -? {DECIMAL_DIGIT}+

%xstate PREPROCESSOR
%xstate STRING
%xstate RAW_STRING
%xstate HARD_STRING
%xstate CHARACTER

%%

{WS}+                 { return WHITE_SPACE; }
{NL}                  { return WHITE_SPACE; }
{CONT}                { return ESCAPING_SLASH; }
{DOC_COMMENT}         { return DOC_COMMENT; }
{C_STYLE_COMMENT}     { return C_STYLE_COMMENT; }
{END_OF_LINE_COMMENT} { return EOL_COMMENT; }

"#"                   { yybegin(PREPROCESSOR); return HASH; }

<PREPROCESSOR> {
  "assert"            { yybegin(YYINITIAL); return ASSERT_DIRECTIVE; }
  "define"            { yybegin(YYINITIAL); return DEFINE_DIRECTIVE; }
  "else"              { yybegin(YYINITIAL); return ELSE_DIRECTIVE; }
  "elseif"            { yybegin(YYINITIAL); return ELSEIF_DIRECTIVE; }
  "endif"             { yybegin(YYINITIAL); return ENDIF_DIRECTIVE; }
  "endinput"          { yybegin(YYINITIAL); return ENDINPUT_DIRECTIVE; }
  "endscript"         { yybegin(YYINITIAL); return ENDSCRIPT_DIRECTIVE; }
  "error"             { yybegin(YYINITIAL); return ERROR_DIRECTIVE; }
  "warning"           { yybegin(YYINITIAL); return WARNING_DIRECTIVE; }
  "file"              { yybegin(YYINITIAL); return FILE_DIRECTIVE; }
  "if"                { yybegin(YYINITIAL); return IF_DIRECTIVE; }
  "include"           { yybegin(YYINITIAL); return INCLUDE_DIRECTIVE; }
  "line"              { yybegin(YYINITIAL); return LINE_DIRECTIVE; }
  "pragma"            { yybegin(YYINITIAL); return PRAGMA_DIRECTIVE; }
  "tryinclude"        { yybegin(YYINITIAL); return TRYINCLUDE_DIRECTIVE; }
  "undef"             { yybegin(YYINITIAL); return UNDEF_DIRECTIVE; }
  [^]                 { yybegin(YYINITIAL); yypushback(1); }
}

"true"                |
"false"               { return BOOLEAN_LITERAL; }
{BINARY_LITERAL}      |
{DECIMAL_LITERAL}     |
{OCTAL_LITERAL}       |
{HEXADECIMAL_LITERAL} { return CELL_LITERAL; }
{RATIONAL_LITERAL}    { return RATIONAL_LITERAL; }

"acquire"             { return AQUIRE_KEYWORD; }
"as"                  { return AS_KEYWORD; }
"assert"              { return ASSERT_KEYWORD; }
"break"               { return BREAK_KEYWORD; }
"builtin"             { return BUILTIN_KEYWORD; }
"catch"               { return CATCH_KEYWORD; }
"case"                { return CASE_KEYWORD; }
"cast_to"             { return CAST_TO_KEYWORD; }
"cellsof"             { return CELLSOF_KEYWORD; }
"char"                { return CHAR_KEYWORD; }
"const"               { return CONST_KEYWORD; }
"continue"            { return CONTINUE_KEYWORD; }
"decl"                { return DECL_KEYWORD; }
"default"             { return DEFAULT_KEYWORD; }
"defined"             { return DEFINED_KEYWORD; }
"delete"              { return DELETE_KEYWORD; }
"do"                  { return DO_KEYWORD; }
"double"              { return DOUBLE_KEYWORD; }
"else"                { return ELSE_KEYWORD; }
"enum"                { return ENUM_KEYWORD; }
"exit"                { return EXIT_KEYWORD; }
"explicit"            { return EXPLICIT_KEYWORD; }
"finally"             { return FINALLY_KEYWORD; }
"for"                 { return FOR_KEYWORD; }
"foreach"             { return FOREACH_KEYWORD; }
"forward"             { return FORWARD_KEYWORD; }
"funcenum"            { return FUNCENUM_KEYWORD; }
"functag"             { return FUNCTAG_KEYWORD; }
"function"            { return FUNCTION_KEYWORD; }
"goto"                { return GOTO_KEYWORD; }
"if"                  { return IF_KEYWORD; }
"implicit"            { return IMPLICIT_KEYWORD; }
"import"              { return IMPORT_KEYWORD; }
"in"                  { return IN_KEYWORD; }
"int"                 { return INT_KEYWORD; }
"int8"                { return INT8_KEYWORD; }
"int16"               { return INT16_KEYWORD; }
"int32"               { return INT32_KEYWORD; }
"int64"               { return INT64_KEYWORD; }
"interface"           { return INTERFACE_KEYWORD; }
"intn"                { return INTN_KEYWORD; }
"let"                 { return LET_KEYWORD; }
"methodmap"           { return METHODMAP_KEYWORD; }
"namespace"           { return NAMESPACE_KEYWORD; }
"native"              { return NATIVE_KEYWORD; }
"new"                 { return NEW_KEYWORD; }
"null"                { return NULL_KEYWORD; }
"__nullable__"        { return NULLABLE_KEYWORD; }
"object"              { return OBJECT_KEYWORD; }
"operator"            { return OPERATOR_KEYWORD; }
"package"             { return PACKAGE_KEYWORD; }
"private"             { return PRIVATE_KEYWORD; }
"protected"           { return PROTECTED_KEYWORD; }
"public"              { return PUBLIC_KEYWORD; }
"readonly"            { return READONLY_KEYWORD; }
"return"              { return RETURN_KEYWORD; }
"sealed"              { return SEALED_KEYWORD; }
"sizeof"              { return SIZEOF_KEYWORD; }
"sleep"               { return SLEEP_KEYWORD; }
"static"              { return STATIC_KEYWORD; }
"stock"               { return STOCK_KEYWORD; }
"struct"              { return STRUCT_KEYWORD; }
"switch"              { return SWITCH_KEYWORD; }
"tagof"               { return TAGOF_KEYWORD; }
"this"                { return THIS_KEYWORD; }
"throw"               { return THROW_KEYWORD; }
"try"                 { return TRY_KEYWORD; }
"typedef"             { return TYPEDEF_KEYWORD; }
"typeof"              { return TYPEOF_KEYWORD; }
"typeset"             { return TYPESET_KEYWORD; }
"uint8"               { return UINT8_KEYWORD; }
"uint16"              { return UINT16_KEYWORD; }
"uint32"              { return UINT32_KEYWORD; }
"uint64"              { return UINT64_KEYWORD; }
"uintn"               { return UINTN_KEYWORD; }
"union"               { return UNION_KEYWORD; }
"using"               { return USING_KEYWORD; }
"var"                 { return VAR_KEYWORD; }
"variant"             { return VARIANT_KEYWORD; }
"view_as"             { return VIEW_AS_KEYWORD; }
"virtual"             { return VIRTUAL_KEYWORD; }
"void"                { return VOID_KEYWORD; }
"volatile"            { return VOLATILE_KEYWORD; }
"while"               { return WHILE_KEYWORD; }
"with"                { return WITH_KEYWORD; }

{IDENTIFIER}          { return IDENTIFIER; }

"=="                  { return EQEQ; }
"!="                  { return EXCLEQ; }
"||"                  { return OROR; }
"++"                  { return PLUSPLUS; }
"--"                  { return MINUSMINUS; }

"<"                   { return LT; }
"<="                  { return LTEQ; }
"<<="                 { return LTLTEQ; }
"<<"                  { return LTLT; }
">"                   { return GT; }
">="                  { return GTEQ; } // Not defined in _JavaLexer.flex
">>="                 { return GTGTEQ; } // Not defined in _JavaLexer.flex
">>"                  { return GTGT; } // Not defined in _JavaLexer.flex
">>>="                { return GTGTGTEQ; } // Not defined in _JavaLexer.flex
">>>"                 { return GTGTGT; } // Not defined in _JavaLexer.flex
"&"                   { return AND; }
"&&"                  { return ANDAND; }

"+="                  { return PLUSEQ; }
"-="                  { return MINUSEQ; }
"*="                  { return ASTERISKEQ; }
"/="                  { return DIVEQ; }
"&="                  { return ANDEQ; }
"|="                  { return OREQ; }
"^="                  { return XOREQ; }
"%="                  { return PERCEQ; }

"("                   { return LPARENTH; }
")"                   { return RPARENTH; }
"{"                   { return LBRACE; }
"}"                   { return RBRACE; }
"["                   { return LBRACKET; }
"]"                   { return RBRACKET; }
","                   { return COMMA; }
"..."                 { return DOTDOTDOT; }
".."                  { return DOTDOT; }
"."                   { return DOT; }

"="                   { return EQ; }
//"!"                 { return EXCL; } // Handled below in string lexing
"~"                   { return TILDE; }
"?"                   { return QUEST; }
":"                   { return COLON; }
"+"                   { return PLUS; }
"-"                   { return MINUS; }
"*"                   { return ASTERISK; }
"/"                   { return DIV; }
"|"                   { return OR; }
"^"                   { return XOR; }
"%"                   { return PERC; }
"@"                   { return AT; }
";"                   { return SEMICOLON; }
//"#"                 { return HASH; }

"_"                   { return UNDERSCORE; }
"::"                  { return COLONCOLON; }

"'"                   { yybegin(CHARACTER); }

"\""                  { yybegin(STRING); packed = false; }
"!\""                 { yybegin(STRING); packed = true; }
"!" . "\""            { if (yycharat(1) == sc_ctrlchar) {
                          yybegin(RAW_STRING);
                          packed = true;
                        } else {
                          yypushback(2);
                          return EXCL;
                       }
                      }
"!"                   { return EXCL; } // Handled below in string lexing

[^]                   { if (yycharat(0) == sc_ctrlchar) {
                          yybegin(HARD_STRING);
                        } else {
                          return BAD_CHARACTER;
                        }
                      }

<STRING> {
  "\""                { yybegin(YYINITIAL); return packed ? PACKED_STRING_LITERAL : STRING_LITERAL; }
  {CONT}              {}
  [^\"\r\n][^\r\n]    { if (yycharat(0) != sc_ctrlchar) {
                          yypushback(1);
                        }
                      }
  [^\r\n]             {}
  [^]                 { yybegin(YYINITIAL); yypushback(1); return packed ? PACKED_STRING_LITERAL : STRING_LITERAL; }
  <<EOF>>             { yybegin(YYINITIAL); return packed ? PACKED_STRING_LITERAL : STRING_LITERAL; }
}

<RAW_STRING> {
  "\""                { yybegin(YYINITIAL); return packed ? PACKED_RAW_STRING_LITERAL : RAW_STRING_LITERAL; }
  {CONT}              |
  [^\r\n]             {}
  [^]                 { yybegin(YYINITIAL); return packed ? PACKED_RAW_STRING_LITERAL : RAW_STRING_LITERAL; }
  <<EOF>>             { yybegin(YYINITIAL); return packed ? PACKED_RAW_STRING_LITERAL : RAW_STRING_LITERAL; }
}

<HARD_STRING> {
  "!" "\""            { yybegin(RAW_STRING); packed = true; }
  "\""                { yybegin(RAW_STRING); packed = false; }
  [^]                 { yybegin(YYINITIAL); yypushback(yylength()); return BAD_CHARACTER; }
}

<CHARACTER> {
  "'"                 { yybegin(YYINITIAL); return CHARACTER_LITERAL; }
  {CONT}              {}
  [^\'\r\n][^\r\n]    { if (yycharat(0) != sc_ctrlchar) {
                          yypushback(1);
                        }
                      }
  [^\r\n]             {}
  [^]                 { yybegin(YYINITIAL); yypushback(1); return CHARACTER_LITERAL; }
  <<EOF>>             { yybegin(YYINITIAL); return CHARACTER_LITERAL; }
}

//[^]                 { return BAD_CHARACTER; } // Not needed
