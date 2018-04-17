package net.alliedmods.lang.sourcepawn.psi.preprocessor.stub;

import com.intellij.psi.stubs.StubElement;

import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiIncludeStatement;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiSpFileReference;

public interface PsiIncludeStatementStub extends StubElement<PsiIncludeStatement> {
  String getIncludeReferenceText();
  PsiSpFileReference getReference();
}
