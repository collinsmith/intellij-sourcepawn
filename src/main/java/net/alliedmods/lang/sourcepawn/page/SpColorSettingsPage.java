package net.alliedmods.lang.sourcepawn.page;

import com.intellij.codeHighlighting.RainbowHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.RainbowColorSettingsPage;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;

import net.alliedmods.lang.sourcepawn.SpBundle;
import net.alliedmods.lang.sourcepawn.SpIcons;
import net.alliedmods.lang.sourcepawn.SpLanguage;
import net.alliedmods.lang.sourcepawn.highlighter.SpHighlightingColors;
import net.alliedmods.lang.sourcepawn.highlighter.SpSyntaxHighlighter;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.*;

public class SpColorSettingsPage implements RainbowColorSettingsPage, DisplayPrioritySortable {

  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.keyword"), SpHighlightingColors.KEYWORD),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.number"), SpHighlightingColors.NUMBER),

      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.string"), SpHighlightingColors.STRING),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.valid.escape.in.string"), SpHighlightingColors.VALID_STRING_ESCAPE),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.invalid.escape.in.string"), SpHighlightingColors.INVALID_STRING_ESCAPE),
      
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.operator.sign"), SpHighlightingColors.OPERATION_SIGN),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.parentheses"), SpHighlightingColors.PARENTHESES),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.braces"), SpHighlightingColors.BRACES),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.brackets"), SpHighlightingColors.BRACKETS),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.comma"), SpHighlightingColors.COMMA),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.semicolon"), SpHighlightingColors.SEMICOLON),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.dot"), SpHighlightingColors.DOT),
  
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.line.comment"), SpHighlightingColors.LINE_COMMENT),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.block.comment"), SpHighlightingColors.BLOCK_COMMENT),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.javadoc.comment"), SpHighlightingColors.DOC_COMMENT),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.javadoc.tag"), SpHighlightingColors.DOC_COMMENT_TAG),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.javadoc.tag.value"), SpHighlightingColors.DOC_COMMENT_TAG_VALUE),
      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.javadoc.markup"), SpHighlightingColors.DOC_COMMENT_MARKUP),

      new AttributesDescriptor(SpBundle.message("options.sp.attribute.descriptor.preprocessor"), SpHighlightingColors.PREPROCESSOR)
  };

  @NonNls
  private static final Map<String, TextAttributesKey> ourTags = RainbowHighlighter.createRainbowHLM();
  static {
    ourTags.put("javadocTagValue", SpHighlightingColors.DOC_COMMENT_TAG_VALUE);
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return SpBundle.message("sdk.presentableName");
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return SpIcons.SOURCEPAWN;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new SpSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    try {
      final InputStream stream = SpColorSettingsPage.class.getResourceAsStream("/demo.sp");
      String str = StreamUtil.convertSeparators(StreamUtil.readText(stream, "UTF-8"));
      System.out.println(str);
      return str;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return ourTags;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @Override
  public boolean isRainbowType(TextAttributesKey type) {
    return SpHighlightingColors.DOC_COMMENT_TAG_VALUE.equals(type);
  }

  @Nullable
  @Override
  public Language getLanguage() {
    return SpLanguage.INSTANCE;
  }

  @Override
  public DisplayPriority getPriority() {
    return DisplayPriority.KEY_LANGUAGE_SETTINGS;
  }

}
