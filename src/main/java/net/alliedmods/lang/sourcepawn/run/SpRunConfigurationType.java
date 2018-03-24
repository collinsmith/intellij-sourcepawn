package net.alliedmods.lang.sourcepawn.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

import net.alliedmods.lang.sourcepawn.SpIcons;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SpRunConfigurationType implements ConfigurationType {

  private final ConfigurationFactory factory;

  public SpRunConfigurationType() {
    factory = new ConfigurationFactory(this) {
      @NotNull
      @Override
      public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new SpRunConfiguration(project, this, "SourceMod Plugin");
      }
    };
  }

  @Override
  public String getDisplayName() {
    return "SourceMod Plugin";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "Compile SourceMod Plugin";
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[] { factory };
  }

  @NotNull
  @Override
  public String getId() {
    return "SourceModRunConfigurationType";
  }

  @Override
  public Icon getIcon() {
    return SpIcons.RUN_CONFIGURATION;
  }
}
