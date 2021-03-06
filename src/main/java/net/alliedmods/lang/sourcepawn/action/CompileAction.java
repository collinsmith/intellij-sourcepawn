package net.alliedmods.lang.sourcepawn.action;

import com.intellij.execution.filters.RegexpFilter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;

import net.alliedmods.lang.sourcepawn.SpBundle;
import net.alliedmods.lang.sourcepawn.SpFileType;
import net.alliedmods.lang.sourcepawn.SpSupport;
import net.alliedmods.lang.sourcepawn.build.BuildConfiguration;
import net.alliedmods.lang.sourcepawn.build.BuildUtils;
import net.alliedmods.lang.sourcepawn.build.ConsoleBuilder;
import net.alliedmods.lang.sourcepawn.build.ImmutableBuildConfiguration;
import net.alliedmods.lang.sourcepawn.psi.PsiSpFile;
import net.alliedmods.lang.sourcepawn.sdk.SpSdkType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Paths;

public class CompileAction extends AnAction {

  @Override
  public void update(AnActionEvent e) {
    PsiFile file = LangDataKeys.PSI_FILE.getData(e.getDataContext());
    e.getPresentation().setEnabledAndVisible(file instanceof PsiSpFile
        && file.getFileType() == SpFileType.INSTANCE
        && SpSupport.getScriptExtensions().contains(file.getVirtualFile().getExtension())
    );
  }

  @Override
  public void actionPerformed(AnActionEvent e) {
    PsiFile psiFile = LangDataKeys.PSI_FILE.getData(e.getDataContext());
    // FIXME: This will not work with include files.
    if (!(psiFile instanceof PsiSpFile) || psiFile.getFileType() != SpFileType.INSTANCE) {
      return;
    }

    Project project = e.getProject();
    VirtualFile file = e.getData(LangDataKeys.VIRTUAL_FILE);
    compile(project, file);
  }

  private void compile(@NotNull Project project, @NotNull VirtualFile file) {
    compile(project, file, null);
  }

  private void compile(@NotNull Project project, @NotNull VirtualFile file, @Nullable BuildConfiguration config) {
    /*if (!ApSupport.isApFile(file)) {
      Messages.showErrorDialog(project, ApBundle.message("amxx.error.compiler.filetype.msg", file),
          ApBundle.message("amxx.error.compiler.filetype.title"));
      return;
    }*/

    if (config == null) {
      Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();
      if (sdk == null) {
        Messages.showErrorDialog(project,
            SpBundle.message("sp.error.compiler.sdk.missing.msg"),
            SpBundle.message("sp.error.compiler.sdk.missing.title"));
        return;
      } else if (!(sdk.getSdkType() instanceof SpSdkType)) {
        Messages.showErrorDialog(project,
            SpBundle.message("sp.error.compiler.sdk.invalid.msg", sdk),
            SpBundle.message("sp.error.compiler.sdk.invalid.title"));
        return;
      }

      String process = SpSdkType.getCompilerPath(sdk);
      if (process == null || process.isEmpty()) {
        Messages.showErrorDialog(project,
            SpBundle.message("sp.error.compiler.missing.msg", process),
            SpBundle.message("sp.error.compiler.missing.title"));
        return;
      }

      config = BuildConfiguration.create()
          .setProcess(process)
          .setWorkingDirectory(Paths.get(file.getParent().getPath()))
          .appendTarget(Paths.get(file.getPath()));
          // My projects won't compile without:
          //.appendArg("\\")
          //.appendArg(";");
    }

    ImmutableBuildConfiguration savedBuildConfiguration = config.commit();
    ConsoleBuilder builder = new ConsoleBuilder(file.getNameWithoutExtension(), project)
        .setBuildConfiguration(savedBuildConfiguration)
        .setCompilerOutputFilter(new RegexpFilter(project,
            RegexpFilter.FILE_PATH_MACROS + "\\(" + RegexpFilter.LINE_MACROS + "( -- \\d+)?" + "\\)"))
        .setRerunAction(() -> compile(project, file, savedBuildConfiguration));
    // TODO: Add support for settings
    //if (ApSettings.getInstance().saveDocuments) {
      BuildUtils.saveDocuments();
    //}

    builder.build();
  }

}
