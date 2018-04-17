package net.alliedmods.lang.sourcepawn.psi.preprocessor.stub;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.impl.source.tree.LightTreeUtil;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.io.StringRef;

import net.alliedmods.lang.sourcepawn.psi.SpElementType;
import net.alliedmods.lang.sourcepawn.psi.SpElementTypes;
import net.alliedmods.lang.sourcepawn.psi.SpPreprocessorElementTypes;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiIncludeStatement;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiSpFileReference;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.impl.PsiIncludeStatementImpl;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.stub.impl.PsiIncludeStatementStubImpl;
import net.alliedmods.lang.sourcepawn.psi.stub.SpStubElementType;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class SpIncludeStatementElementType
    extends SpStubElementType<PsiIncludeStatementStub, PsiIncludeStatement> {

  public SpIncludeStatementElementType(@NotNull @NonNls String debugName) {
    super(debugName);
  }

  @Override
  public PsiIncludeStatement createPsi(@NotNull PsiIncludeStatementStub stub) {
    return getPsiFactory(stub).createIncludeStatement(stub);
  }

  @Override
  public PsiIncludeStatement createPsi(@NotNull ASTNode node) {
    return new PsiIncludeStatementImpl(node);
  }

  @NotNull
  @Override
  public PsiIncludeStatementStub createStub(@NotNull PsiIncludeStatement psi, StubElement parentStub) {
    PsiSpFileReference ref = psi.getIncludeReference();
    return new PsiIncludeStatementStubImpl(parentStub, ref != null ? ref.getCanonicalText() : null);
  }

  @Override
  public PsiIncludeStatementStub createStub(LighterAST tree, LighterASTNode node, StubElement parentStub) {
    String refText = null;
    for (LighterASTNode child : tree.getChildren(node)) {
      IElementType type = child.getTokenType();
      if (type == SpPreprocessorElementTypes.FILE_REFERENCE) {
        refText = LightTreeUtil.toFilteredString(tree, node, SpElementTypes.SP_COMMENT_OR_WHITESPACE);
      }
    }

    return new PsiIncludeStatementStubImpl(parentStub, refText);
  }

  @Override
  public void serialize(@NotNull PsiIncludeStatementStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    dataStream.writeName(stub.getIncludeReferenceText());
  }

  @NotNull
  @Override
  public PsiIncludeStatementStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
    StringRef refText = dataStream.readName();
    return new PsiIncludeStatementStubImpl(parentStub, refText);
  }

  @Override
  public void indexStub(@NotNull PsiIncludeStatementStub stub, @NotNull IndexSink sink) {}
}
