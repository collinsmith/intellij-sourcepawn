package net.alliedmods.lang.sourcepawn.psi.preprocessor.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.reference.SoftReference;
import com.intellij.util.io.StringRef;

import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiIncludeStatement;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.PsiSpFileReference;
import net.alliedmods.lang.sourcepawn.psi.preprocessor.stub.PsiIncludeStatementStub;
import net.alliedmods.lang.sourcepawn.psi.stub.SpStubElementTypes;

public class PsiIncludeStatementStubImpl
    extends StubBase<PsiIncludeStatement>
    implements PsiIncludeStatementStub {

  private final StringRef text;

  private SoftReference<PsiSpFileReference> reference = null;

  public PsiIncludeStatementStubImpl(StubElement parent, String text) {
    this(parent, StringRef.fromString(text));
  }

  public PsiIncludeStatementStubImpl(StubElement parent, StringRef text) {
    super(parent, SpStubElementTypes.INCLUDE_STATEMENT);
    this.text = text;
  }

  @Override
  public String getIncludeReferenceText() {
    return StringRef.toString(text);
  }

  @Override
  public PsiSpFileReference getReference() {
    if (reference == null) {
      reference = new SoftReference<>(createReference());
    }

    return reference.get();
  }

  private PsiSpFileReference createReference() {
    String refText = getIncludeReferenceText();
    if (refText == null) {
      return null;
    }

    System.out.println("createReference().refText=" + refText);
    // TODO: PsiSpParserFacadeImpl.createReferenceFromText
    return null;
  }

  @Override
  public String toString() {
    return "PsiIncludeStatementStub[" + getIncludeReferenceText() + "]";
  }

}
