package net.alliedmods.lang.sourcepawn.lexer;

import java.util.concurrent.CopyOnWriteArrayList;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;

import static net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes.*;

%%

%{
  //...
%}

%unicode
%class _SpCommandLexer
%implements FlexLexer
%function advance
%type IElementType

WS = [\ \t\f]
NL = \r|\n|\r\n
CONT = "\\" {WS}* {NL} {WS}*

C_STYLE_COMMENT = ("/*"[^"*"]{COMMENT_TAIL})|"/*"
DOC_COMMENT = "/*""*"+("/"|([^"/""*"]{COMMENT_TAIL}))?
COMMENT_TAIL = ([^"*"]*("*"+[^"*""/"])?)*("*"+"/")?
END_OF_LINE_COMMENT = "/""/"[^\r\n]*

%%

{DOC_COMMENT}         { return DOC_COMMENT; }
{C_STYLE_COMMENT}     { return C_STYLE_COMMENT; }
{END_OF_LINE_COMMENT} { return EOL_COMMENT; }

{CONT}                {  }

{NL}                  { return NEW_LINE; }
<<EOF>>               { return NEW_LINE; }

"#"                   {  }

[^]                   {  }
