package net.alliedmods.lang.sourcepawn.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;

import java.util.PrimitiveIterator;

public abstract class SpCommandLexer extends LexerBase {

  IElementType CMD_NONE = new SpTokenType("CMD_NONE");
  IElementType CMD_TERM = new SpTokenType("CMD_TERM");
  IElementType CMD_CONDFALSE = new SpTokenType("CMD_CONDFALSE");
  IElementType CMD_EMPTYLINE = new SpTokenType("CMD_EMPTYLINE");
  IElementType CMD_INCLUDE = new SpTokenType("CMD_INCLUDE");
  IElementType CMD_DEFINE = new SpTokenType("CMD_DEFINE");
  IElementType CMD_IF = new SpTokenType("CMD_IF");
  IElementType CMD_DIRECTIVE = new SpTokenType("CMD_DIRECTIVE");

  private CharSequence buffer;
  private char[] cBuffer;
  private int bufferStartOffset;
  private int bufferEndOffset;
  private int tokenEndOffset;
  private IElementType tokenType;

  private BufferIterator iterator;

  protected final char charAt(int offset) {
    return cBuffer != null ? cBuffer[offset] : buffer.charAt(offset);
  }

  private void _locateToken() {
    if (tokenEndOffset == bufferEndOffset) {
      tokenType = null;
      bufferStartOffset = bufferEndOffset;
      return;
    }

    bufferStartOffset = tokenEndOffset;
    iterator.goTo(bufferStartOffset);

    int codePoint = iterator.nextInt();
    if (isWhitespace(codePoint)) {

    }
  }

  private static boolean isWhitespace(int codePoint) {
    return codePoint <= ' ';
  }

  private int getWhitespaces() {
    if (!iterator.hasNext()) {
      return iterator.offset;
    }

    int offset = bufferStartOffset;
    char c = charAt(offset);
    while (isWhitespace(c)) {
      offset++;
      if (offset >= bufferEndOffset) return bufferEndOffset;
      c = charAt(offset);
    }

    return offset;
  }

  private class BufferIterator implements PrimitiveIterator.OfInt {
    int offset;

    public void goTo(int offset) {
      this.offset = offset;
    }

    @Override
    public int nextInt() {
      return charAt(offset++);
    }

    @Override
    public boolean hasNext() {
      return offset < bufferEndOffset;
    }
  }
}
