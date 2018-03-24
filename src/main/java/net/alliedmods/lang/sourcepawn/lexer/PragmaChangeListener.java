package net.alliedmods.lang.sourcepawn.lexer;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public interface PragmaChangeListener {

  void onPragmaChanged(@NotNull @NonNls String pragma, Object value);

}
