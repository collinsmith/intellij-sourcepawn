package net.alliedmods.lang.sourcepawn.psi.stub;

import com.intellij.psi.PsiFile;
import com.intellij.psi.StubBuilder;
import com.intellij.psi.stubs.DefaultStubBuilder;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.tree.IStubFileElementType;

import net.alliedmods.lang.sourcepawn.SpLanguage;
import net.alliedmods.lang.sourcepawn.psi.PsiSpFile;
import net.alliedmods.lang.sourcepawn.psi.stub.impl.PsiSpFileStubImpl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface PsiSpFileStub extends PsiFileStub<PsiSpFile> {

  static IStubFileElementType<PsiSpFileStub> newStubFileElementType() {
    return new IStubFileElementType<PsiSpFileStub>(SpLanguage.INSTANCE) {
      @Override
      public int getStubVersion() {
        return 100;
      }

      @Override
      public StubBuilder getBuilder() {
        return new DefaultStubBuilder() {
          @NotNull
          @Override
          protected StubElement createStubForFile(@NotNull PsiFile file) {
            if (file instanceof PsiSpFile) {
              return new PsiSpFileStubImpl((PsiSpFile) file);
            }

            return super.createStubForFile(file);
          }
        };
      }

      @Override
      public void serialize(@NotNull PsiSpFileStub stub, @NotNull StubOutputStream dataStream) throws IOException {}

      @NotNull
      @Override
      public PsiSpFileStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new PsiSpFileStubImpl(null);
      }

      @NotNull
      @Override
      public String getExternalId() {
        return "sp.file";
      }
    };
  }

}
