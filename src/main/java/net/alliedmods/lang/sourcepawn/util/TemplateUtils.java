package net.alliedmods.lang.sourcepawn.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;

import net.alliedmods.lang.sourcepawn.module.SpModuleBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TemplateUtils {

  public static String getTemplateText(String fileName, String... additionalParameters)
      throws IOException {
    Properties properties = FileTemplateManager.getDefaultInstance().getDefaultProperties();
    if (additionalParameters != null) {
      for (int i = 0; i < additionalParameters.length; ++i) {
        String paramName = additionalParameters[i];
        if (i + 1 < additionalParameters.length) {
          ++i;
          properties.put(paramName, additionalParameters[i]);
        }
      }
    }

    final FileTemplate fileTemplate
        = FileTemplateManager.getDefaultInstance().getInternalTemplate(fileName);
    assert fileTemplate != null;
    return fileTemplate.getText(properties);
  }

  public static void createOrResetFileContent(VirtualFile sourcePathDir,
                                              String fileName,
                                              InputStream inputStream)
      throws IOException {
    VirtualFile child = sourcePathDir.findChild(fileName);
    if (child == null) child = sourcePathDir.createChildData(SpModuleBuilder.class, fileName);
    OutputStream outputStream = child.getOutputStream(SpModuleBuilder.class);

    FileUtil.copy(inputStream, outputStream);
    outputStream.flush();
    outputStream.close();
  }

  public static void createOrResetFileContentFromTemplate(VirtualFile sourcePathDir,
                                                          String fileName,
                                                          String templateFileName,
                                                          String... additionalParameters) {
    try {
      String templateText = getTemplateText(templateFileName, additionalParameters);
      InputStream inputStream = new ByteArrayInputStream(
          templateText.getBytes(StandardCharsets.UTF_8));
      createOrResetFileContent(sourcePathDir, fileName, inputStream);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private TemplateUtils() {}

}
