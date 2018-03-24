package net.alliedmods.lang.sourcepawn;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.VirtualFile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SpFileType extends LanguageFileType {

  public static final SpFileType INSTANCE = new SpFileType();

  public static boolean isSpFile(@NotNull VirtualFile file) {
    return file.getFileType() instanceof SpFileType;
  }

  private SpFileType() {
    super(SpLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "SourcePawn";
  }

  @NotNull
  @Override
  public String getDescription() {
    return SpBundle.message("sp.filetype");
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return SpSupport.SP;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return SpIcons.FILE;
  }

}
