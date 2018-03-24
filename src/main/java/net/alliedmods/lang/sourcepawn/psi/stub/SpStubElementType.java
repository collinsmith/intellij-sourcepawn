package net.alliedmods.lang.sourcepawn.psi.stub;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IStubFileElementType;

import net.alliedmods.lang.sourcepawn.SpLanguage;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public abstract class SpStubElementType<StubT extends StubElement, PsiT extends PsiElement>
  extends IStubElementType<StubT, PsiT> {

  public SpStubElementType(@NotNull @NonNls String debugName) {
    super(debugName, SpLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public final String getExternalId() {
    return "sp." + super.toString();
  }

  protected boolean createStubIfParentIsStub(ASTNode node) {
    final ASTNode parent = node.getTreeParent();
    final IElementType parentType = parent.getElementType();
    return (parentType instanceof IStubElementType && ((IStubElementType) parentType).shouldCreateStub(parent))
        || parentType instanceof IStubFileElementType;
  }

}
