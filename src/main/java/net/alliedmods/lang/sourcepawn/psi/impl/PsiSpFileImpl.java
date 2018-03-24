package net.alliedmods.lang.sourcepawn.psi.impl;

import com.google.common.io.Files;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;

import net.alliedmods.lang.sourcepawn.SpFileType;
import net.alliedmods.lang.sourcepawn.SpIcons;
import net.alliedmods.lang.sourcepawn.SpLanguage;
import net.alliedmods.lang.sourcepawn.SpSupport;
import net.alliedmods.lang.sourcepawn.psi.PsiSpFile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PsiSpFileImpl extends PsiFileBase implements PsiSpFile {

  private static final boolean SET_ICONS_WITH_SVT = false;
  private static final boolean SHORTEN_FILENAMES = true;

  public PsiSpFileImpl(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, SpLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return SpFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "PsiSpFile:" + getName();
  }

  @Nullable
  @Override
  public Icon getIcon(int flags) {
    if (SET_ICONS_WITH_SVT) {
      return super.getIcon(flags);
    } else {
      final String virtualExtension = getVirtualFile().getExtension();
      if (SpSupport.getIncludeExtensions().contains(virtualExtension)) {
        return SpIcons.NODE_INCLUDE;
      }

      return SpIcons.NODE_SCRIPT;
    }
  }

  @NotNull
  @Override
  public String getName() {
    if (!SHORTEN_FILENAMES) {
      return super.getName();
    } else {
      return Files.getNameWithoutExtension(super.getName());
    }
  }

  @Override
  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    if (SHORTEN_FILENAMES) {
      final String virtualExtension = getVirtualFile().getExtension();
      if (Files.getFileExtension(name).isEmpty()) {
        if (SpSupport.getScriptExtensions().contains(virtualExtension)) {
          name += ("." + SpSupport.SP);
        } else if (SpSupport.getIncludeExtensions().contains(virtualExtension)) {
          name += ("." + SpSupport.INC);
        }
      }
    }

    return super.setName(name);
  }

}
