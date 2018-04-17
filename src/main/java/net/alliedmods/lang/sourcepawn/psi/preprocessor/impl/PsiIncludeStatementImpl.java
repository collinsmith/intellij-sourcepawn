package net.alliedmods.lang.sourcepawn.psi.preprocessor.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.ChildRole;
import com.intellij.psi.util.PsiUtilCore;

import net.alliedmods.lang.sourcepawn.psi.SpStubPsiElement;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiIncludeStatement;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiSpFileReference;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.stub.PsiIncludeStatementStub;

public class PsiIncludeStatementImpl
    extends SpStubPsiElement<PsiIncludeStatementStub>
    implements PsiIncludeStatement {

  public PsiIncludeStatementImpl(ASTNode node) {
    super(node);
  }

  @Override
  public PsiSpFileReference getIncludeReference() {
    PsiUtilCore.ensureValid(this);
    PsiIncludeStatementStub stub = getStub();
    if (stub != null) {
      return stub.getReference();
    }

    return (PsiSpFileReference) calcTreeElement()
        .findChildByRoleAsPsiElement(ChildRole.INCLUDE_REFERENCE);
  }

  @Override
  public PsiElement resolve() {
    return null;
  }
}
