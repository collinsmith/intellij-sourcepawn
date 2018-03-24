package net.alliedmods.lang.sourcepawn.psi.preprocessor;

import com.intellij.psi.PsiElement;
import com.intellij.util.ArrayFactory;

public interface PsiIncludeStatement extends PsiElement {

  PsiIncludeStatement[] EMPTY_ARRAY = new PsiIncludeStatement[0];
  ArrayFactory<PsiIncludeStatement> ARRAY_FACTORY = i -> i == 0 ? EMPTY_ARRAY : new PsiIncludeStatement[i];

  PsiSpFileReference getIncludeReference();
  PsiElement resolve();

}
