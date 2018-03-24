package net.alliedmods.lang.sourcepawn;

import com.intellij.lang.Language;

public class SpLanguage extends Language {

  public static final SpLanguage INSTANCE = new SpLanguage();

  private static final String ID = "SourcePawn";

  private SpLanguage() {
    super(ID);
  }

  @Override
  public boolean isCaseSensitive() {
    return true;
  }



}
