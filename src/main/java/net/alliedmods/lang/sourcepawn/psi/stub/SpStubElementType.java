package net.alliedmods.lang.sourcepawn.psi.stub;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.java.stubs.StubPsiFactory;
import com.intellij.psi.stubs.ILightStubElementType;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.ICompositeElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IStubFileElementType;

import net.alliedmods.lang.sourcepawn.SpLanguage;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public abstract class SpStubElementType<StubT extends StubElement, PsiT extends PsiElement>
  extends ILightStubElementType<StubT, PsiT>
  implements ICompositeElementType {

  public SpStubElementType(@NotNull @NonNls String debugName) {
    super(debugName, SpLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public final String getExternalId() {
    return "sp." + super.toString();
  }

  protected StubPsiFactory getPsiFactory(StubT stub) {
    return getFileStub(stub).getPsiFactory();
  }

  private PsiSpFileStub getFileStub(StubT stub) {
    StubElement parent = stub;
    while (!(parent instanceof PsiFileStub)) {
      parent = parent.getParentStub();
    }

    return (PsiSpFileStub) parent;
  }

  public abstract PsiT createPsi(@NotNull ASTNode node);

  @NotNull
  @Override
  public StubT createStub(@NotNull PsiT psi, StubElement stubElement) {
    throw new UnsupportedOperationException("Should not be called. " +
        "Element=" + psi + "; class=" + psi.getClass() + "; " +
        "file=" + (psi.isValid() ? psi.getContainingFile() : "-"));
  }

  protected boolean createStubIfParentIsStub(ASTNode node) {
    final ASTNode parent = node.getTreeParent();
    final IElementType parentType = parent.getElementType();
    return (parentType instanceof IStubElementType && ((IStubElementType) parentType).shouldCreateStub(parent))
        || parentType instanceof IStubFileElementType;
  }

}
