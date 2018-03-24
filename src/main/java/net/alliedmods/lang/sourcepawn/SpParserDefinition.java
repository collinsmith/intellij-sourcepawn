package net.alliedmods.lang.sourcepawn;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.java.lexer.JavaDocLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

import net.alliedmods.lang.sourcepawn.lexer.SpLayeredLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpPreprocessorLexer;
import net.alliedmods.lang.sourcepawn.lexer.SpTokenTypes;
import net.alliedmods.lang.sourcepawn.psi.SpElementTypes;
import net.alliedmods.lang.sourcepawn.psi.impl.PsiSpFileImpl;
import net.alliedmods.lang.sourcepawn.psi.stub.SpStubElementTypes;

import org.jetbrains.annotations.NotNull;

public class SpParserDefinition implements ParserDefinition {

  final IFileElementType FILE = new IFileElementType(SpLanguage.INSTANCE);

  @NotNull
  public static SpLexer createLexer() {
    return new SpLexer();
  }

  @NotNull
  public static Lexer createLayeredLexer() {
    SpLexer spLexer = createLexer();
    SpPreprocessorLexer spPreprocessorLexer = spLexer.createPreprocessorLexer();
    SpLayeredLexer lexer = new SpLayeredLexer(spLexer, spPreprocessorLexer,
        TokenSet.create(SpTokenTypes.PREPROCESSOR_DIRECTIVE));
    return lexer;
  }

  @NotNull
  public static Lexer createDocLexer() {
    return new JavaDocLexer(LanguageLevel.HIGHEST);
  }

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return createLexer();
  }

  @Override
  public PsiParser createParser(Project project) {
    return (root, builder) -> {
      PsiBuilder.Marker marker = builder.mark();
      for (IElementType token;;) {
        builder.advanceLexer();
        token = builder.getTokenType();
        if (token != null) {
          builder.mark().done(token);
          continue;
        }

        break;
      }

      marker.done(root);
      return builder.getTreeBuilt();
    };
  }

  @Override
  public IFileElementType getFileNodeType() {
    return SpStubElementTypes.SP_FILE;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return SpElementTypes.SP_COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return SpElementTypes.SP_STRING_LITERALS;
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return SpElementTypes.SP_WHITESPACES;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return SpElementTypes.Factory.createElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new PsiSpFileImpl(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

}
