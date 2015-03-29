package org.altherian.hboxc.front.gui.action.machine;


import org.altherian.hbox.comm.HypervisorTasks;
import org.altherian.hboxc.front.gui.builder.IconBuilder;
import org.altherian.hboxc.front.gui.vm.MachineLogFileViewer;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class MachineLogGetAction extends AbstractAction {

   private String _srvId;
   private String _vmId;

   public MachineLogGetAction(String srvId, String vmId) {
      super("View Log Files", IconBuilder.getTask(HypervisorTasks.MachineLogFileGet));
      _srvId = srvId;
      _vmId = vmId;
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      MachineLogFileViewer.show(_srvId, _vmId);
   }

}
