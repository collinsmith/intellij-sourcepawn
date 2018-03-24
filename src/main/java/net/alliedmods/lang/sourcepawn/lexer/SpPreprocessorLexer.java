package net.alliedmods.lang.sourcepawn.lexer;

import com.google.common.base.Strings;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.util.Pair;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SpPreprocessorLexer extends FlexAdapter {

  private final Map<String, Pair<Pattern, String>> PATTERNS = new HashMap<>();

  public SpPreprocessorLexer() {
    super(new _SpPreprocessorLexer(null));

    // defined by the compiler at compile-time
    define("__DATE__", "", "__DATE__");
    define("__TIME__", "", "__TIME__");
    define("__BINARY_PATH__", "", "__BINARY_PATH__");
    define("__BINARY_NAME__", "", "__BINARY_NAME__");
  }

  public void addPragmaChangeListener(@NotNull PragmaChangeListener l) {
    ((_SpPreprocessorLexer) getFlex()).addPragmaChangeListener(l);
  }

  public void define(@NotNull @NonNls String prefix, @NotNull @NonNls String postfix) {
    define(prefix, postfix, null);
  }

  public void define(@NotNull @NonNls String prefix, @NotNull @NonNls String postfix, @NonNls String subst) {
    postfix = postfix.replaceAll("%[0-9]", ".*");
    Pair<Pattern, String> tuple = Pair.create(Pattern.compile(postfix), Strings.nullToEmpty(subst));
    PATTERNS.put(prefix, tuple);
  }

  public void undef(@NotNull @NonNls String prefix) {
    PATTERNS.remove(prefix);
  }

  @NotNull
  public String resolve(@NotNull @NonNls String prefix, @NotNull @NonNls String[] args) {
    Pair<Pattern, String> tuple = PATTERNS.get(prefix);
    String subst = tuple.second;
    for (int i = 0; i < args.length; i++) {
      subst = subst.replaceAll("%" + i, args[i]);
    }

    return subst;
  }

}
