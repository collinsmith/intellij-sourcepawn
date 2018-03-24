package net.alliedmods.lang.sourcepawn.module;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import javax.swing.*;

public class SpModuleType extends ModuleType<SpModuleBuilder> {

  @NonNls
  private static final String ID = "SpModule";

  @NotNull
  public static SpModuleType getInstance() {
    return (SpModuleType) ModuleTypeManager.getInstance().findByID(ID);
  }

  @NotNull
  public static Collection<Module> findModules(@NotNull Project project) {
    return ModuleUtil.getModulesOfType(project, SpModuleType.getInstance());
  }

  public SpModuleType() {
    super(ID);
  }

  @NotNull
  @Override
  public SpModuleBuilder createModuleBuilder() {
    return null;
  }

  @NotNull
  @Override
  public String getName() {
    return null;
  }

  @NotNull
  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public Icon getNodeIcon(boolean isOpened) {
    return null;
  }
}
