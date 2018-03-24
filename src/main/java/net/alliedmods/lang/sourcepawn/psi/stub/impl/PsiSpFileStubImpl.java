package net.alliedmods.lang.sourcepawn.psi.stub.impl;

import com.intellij.psi.stubs.PsiFileStubImpl;
import com.intellij.psi.tree.IStubFileElementType;

import net.alliedmods.lang.sourcepawn.psi.PsiSpFile;
import net.alliedmods.lang.sourcepawn.psi.stub.PsiSpFileStub;
import net.alliedmods.lang.sourcepawn.psi.stub.SpStubElementTypes;

import org.jetbrains.annotations.NotNull;

public class PsiSpFileStubImpl extends PsiFileStubImpl<PsiSpFile> implements PsiSpFileStub {

  public PsiSpFileStubImpl(PsiSpFile file) {
    super(file);
  }

  @NotNull
  @Override
  public IStubFileElementType getType() {
    return SpStubElementTypes.SP_FILE;
  }

}
