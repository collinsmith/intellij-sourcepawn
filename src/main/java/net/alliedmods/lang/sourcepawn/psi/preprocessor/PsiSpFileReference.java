package net.alliedmods.lang.sourcepawn.psi.preprocessor;

import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReference;
import com.intellij.util.ArrayFactory;

public interface PsiSpFileReference extends PsiElement, PsiFileReference {

  PsiSpFileReference[] EMPTY_ARRAY = new PsiSpFileReference[0];
  ArrayFactory<PsiSpFileReference> ARRAY_FACTORY = i -> i == 0 ? EMPTY_ARRAY : new PsiSpFileReference[i];

  PsiElement getReferenceNameElement();
  ResolveResult advancedResolve(boolean incompleteCode);
}
