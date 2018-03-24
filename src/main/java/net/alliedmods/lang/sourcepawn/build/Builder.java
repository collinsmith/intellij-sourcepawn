package net.alliedmods.lang.sourcepawn.build;

import com.intellij.execution.process.ProcessHandler;

public interface Builder {

  Process getProcess();

  ProcessHandler getProcessHandler();

  Process build();

}
