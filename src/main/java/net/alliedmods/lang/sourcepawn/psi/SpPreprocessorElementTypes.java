package net.alliedmods.lang.sourcepawn.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

import net.alliedmods.lang.sourcepawn.psi.preprocessor.impl.PsiSpFileReferenceImpl;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.stub.SpIncludeStatementElementType;

import org.jetbrains.annotations.NotNull;

public interface SpPreprocessorElementTypes {
  IElementType FILE_REFERENCE = new SpElementTypes.SpICompositeElementType(
      "FILE_REFERENCE", PsiSpFileReferenceImpl.class);

  IElementType INCLUDE_STATEMENT = new SpIncludeStatementElementType("INCLUDE_STATEMENT") {
    @NotNull
    @Override
    public ASTNode createCompositeNode() {
      return new IncludeStatementElement();
    }
  };
}
