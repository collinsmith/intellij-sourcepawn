package net.alliedmods.lang.sourcepawn.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SpRunSettingsEditor extends SettingsEditor<SpRunConfiguration> {

  private JPanel panel;
  private LabeledComponent<ComponentWithBrowseButton> mainClass;

  public SpRunSettingsEditor(Project project) {}

  @Override
  protected void resetEditorFrom(SpRunConfiguration s) {}

  @Override
  protected void applyEditorTo(SpRunConfiguration s) throws ConfigurationException {}

  @NotNull
  @Override
  protected JComponent createEditor() {
    return panel;
  }

  private void createUIComponents() {
    mainClass = new LabeledComponent<>();
    mainClass.setComponent(new TextFieldWithBrowseButton());
  }

}
