package net.alliedmods.lang.sourcepawn.action;

import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import net.alliedmods.lang.sourcepawn.SpIcons;

import org.jetbrains.annotations.NonNls;

public class CreateSpIncludeAction extends CreateSpScriptAction {

  @NonNls
  private static final String NEW_SOURCEMOD_INCLUDE = "New SourceMod Include";

  @NonNls
  private static final String EMPTY_MODULE_TEMPLATE = "SourceMod Include";

  public CreateSpIncludeAction() {
    super(NEW_SOURCEMOD_INCLUDE, "", SpIcons.FILE);
  }

  @Override
  protected void buildDialog(Project project, PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
    builder.setTitle(NEW_SOURCEMOD_INCLUDE)
        .addKind("Empty Script", SpIcons.FILE, EMPTY_MODULE_TEMPLATE)
        .setValidator(ClassNameValidator.INSTANCE);
  }

  @Override
  protected String getActionName(PsiDirectory directory, String newName, String templateName) {
    return NEW_SOURCEMOD_INCLUDE;
  }

}
