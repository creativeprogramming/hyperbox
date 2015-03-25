package org.altherian.hboxc.front.gui.action.machine;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.altherian.hbox.comm.HypervisorTasks;
import org.altherian.hboxc.front.gui.builder.IconBuilder;
import org.altherian.hboxc.front.gui.vm.MachineLogFileViewer;

public class MachineLogGetAction extends AbstractAction {

   private String _srvId;
   private String _vmId;
   private String _logId;

   public MachineLogGetAction(String srvId, String vmId, String logId) {
      super("View Log Files", IconBuilder.getTask(HypervisorTasks.MachineLogFileGet));

      _srvId = srvId;
      _vmId = vmId;
      _logId = logId;

      setEnabled(true);
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      MachineLogFileViewer.show(_srvId, _vmId);
   }

}
