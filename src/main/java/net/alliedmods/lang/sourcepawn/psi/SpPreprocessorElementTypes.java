package net.alliedmods.lang.sourcepawn.psi;

import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.psi.preprocessor.impl.PsiSpFileReferenceImpl;

public interface SpPreprocessorElementTypes {
  IElementType FILE_REFERENCE = new SpElementTypes.SpICompositeElementType(
      "FILE_REFERENCE", PsiSpFileReferenceImpl.class);
}
