package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.text.CharArrayUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpLexer2 extends LexerBase {

  private final _SpLexer flexLexer;
  private CharSequence buffer;
  private char[] cBuffer;
  private int bufferStartOffset;
  private int bufferEndOffset;
  private int tokenEndOffset;
  private IElementType tokenType;

  private int cell;

  private SpLexer2() {
    this.flexLexer = new _SpLexer(null);
  }

  protected final char charAt(int offset) {
    return cBuffer != null ? cBuffer[offset] : buffer.charAt(offset);
  }

  @Override
  public final void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
    this.buffer = buffer;
    this.cBuffer = CharArrayUtil.fromSequenceWithoutCopying(buffer);
    this.bufferStartOffset = startOffset;
    this.bufferEndOffset = endOffset;
    this.tokenType = null;
    this.tokenEndOffset = startOffset;
    flexLexer.reset(buffer, startOffset, endOffset, 0);
  }

  @Override
  public int getState() {
    return 0;
  }

  @Nullable
  @Override
  public final IElementType getTokenType() {
    if (tokenType == null) _locateToken();
    return tokenType;
  }

  @Override
  public int getTokenStart() {
    return bufferStartOffset;
  }

  @Override
  public int getTokenEnd() {
    if (tokenType == null) _locateToken();
    return tokenEndOffset;
  }

  @Override
  public void advance() {
    if (tokenType == null) _locateToken();
    tokenType = null;
  }

  @NotNull
  @Override
  public CharSequence getBufferSequence() {
    return buffer;
  }

  @Override
  public int getBufferEnd() {
    return bufferEndOffset;
  }

  private void _locateToken() {
    if (tokenEndOffset == bufferEndOffset) {
      tokenType = null;
      bufferStartOffset = bufferEndOffset;
      return;
    }

    bufferStartOffset = tokenEndOffset;

    char c = charAt(bufferStartOffset);
    if (isWhitespace(c)) {
      tokenType = TokenType.WHITE_SPACE;
      tokenEndOffset = getWhitespaces(bufferStartOffset + 1);
    } else {
      switch (c) {
        /**
         * Use states to know when in #define pre
         */
        case '#':
          locateFlexToken();
      }
    }

    if (tokenEndOffset > bufferEndOffset) {
      tokenEndOffset = bufferEndOffset;
    }
  }

  private static final int RAWMODE  = 0x1;
  private static final int UTF8MODE = 0x2;
  private static final int ISPACKED = 0x4;

  private char sc_ctrlchar;

  private int litchar(int offset, int flags) {
    if (offset >= bufferEndOffset) {
      return bufferEndOffset;
    }

    cell = 0;
    char c = charAt(offset);
    if ((flags & RAWMODE) == RAWMODE || c != sc_ctrlchar) {
      cell = c;
    } else {
      offset++;
      if (offset >= bufferEndOffset) {
        return offset;
      }

      c = charAt(offset);
      if (c == sc_ctrlchar) {
        cell = sc_ctrlchar;
        return offset + 1;
      }

      switch (c) {
        case 'a': cell = 7; break;
        case 'b': cell = 8; break;
        case 'e': cell = 27; break;
        case 'f': cell = 12; break;
        case 'n': cell = 10; break;
        case 'r': cell = 13; break;
        case 't': cell = 9; break;
        case 'v': cell = 11; break;
        case '\'':
        case '"':
        case '%':
          cell = c;
          break;
        case 'x':
          offset++;
          if (offset >= bufferEndOffset) return bufferEndOffset;
          c = charAt(offset);

          int digits = 0;
          while (isHexDigit(c) && digits < 2) {
            offset++;
            if (offset >= bufferEndOffset) return bufferEndOffset;
            c = charAt(offset);

            if (isDigit(c)) cell = (cell << 4) + (c - '0');
            else            cell = (cell << 4) + (Character.toLowerCase(c) - 'a' + 10);
            digits++;
          }

          offset++;
          if (offset >= bufferEndOffset) return bufferEndOffset;
          c = charAt(offset);
          if (c == ';') {
            offset++;
          }

          break;
        default:
          if (isDigit(c)) {
            do {
              offset++;
              if (offset >= bufferEndOffset) return bufferEndOffset;
              c = charAt(offset);
              if ('0' <= c && c <= '9') {
                cell = (cell * 10) + (c - '0');
              }
            } while(isDigit(c));
            offset++;
            if (offset >= bufferEndOffset) return bufferEndOffset;
            c = charAt(offset);
            if (c == ';') {
              offset++;
            }
          } else {
            tokenType = TokenType.BAD_CHARACTER;
            return offset;
          }
      }
    }

    return offset;
  }

  private static char parseChar(int flags) {

  }

  private void locateFlexToken() {
  }

  private int getWhitespaces(int offset) {
    if (offset >= bufferEndOffset) {
      return bufferEndOffset;
    }

    char c = charAt(offset);
    while (isWhitespace(c)) {
      offset++;
      if (offset >= bufferEndOffset) return bufferEndOffset;
      c = charAt(offset);
    }

    return offset;
  }

  private static boolean isWhitespace(char c) {
    return c <= ' ';
  }

  private int getDigits(int offset) {
    if (offset >= bufferEndOffset) {
      return bufferEndOffset;
    }

    char c = charAt(offset);
    while (isDigit(c)) {
      offset++;
      if (offset >= bufferEndOffset) return bufferEndOffset;
      c = charAt(offset);
    }

    return offset;
  }

  private static boolean isDigit(char c) {
    switch (c) {
      case '0': case '1': case '2': case '3': case '4':
      case '5': case '6': case '7': case '8': case '9':
        return true;
      default:
        return false;
    }
  }

  private int getHexDigits(int offset) {
    if (offset >= bufferEndOffset) {
      return bufferEndOffset;
    }

    char c = charAt(offset);
    while (isHexDigit(c)) {
      offset++;
      if (offset >= bufferEndOffset) return bufferEndOffset;
      c = charAt(offset);
    }

    return offset;
  }

  private static boolean isHexDigit(char c) {
    switch (c) {
      case '0': case '1': case '2': case '3': case '4':
      case '5': case '6': case '7': case '8': case '9':
      case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
      case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
        return true;
      default:
        return false;
    }
  }

  private int getSemicolon(int offset) {
    if (offset >= bufferEndOffset) {
      return bufferEndOffset;
    }

    char c = charAt(offset);
    return c == ';' ? offset + 1 : offset;
  }

}
