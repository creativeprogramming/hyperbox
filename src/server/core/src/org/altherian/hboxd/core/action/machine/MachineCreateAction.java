/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2013 Maxime Dor
 * 
 * http://kamax.io/hbox/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.altherian.hboxd.core.action.machine;

import org.altherian.hbox.comm.Answer;
import org.altherian.hbox.comm.AnswerType;
import org.altherian.hbox.comm.Command;
import org.altherian.hbox.comm.HypervisorTasks;
import org.altherian.hbox.comm.Request;
import org.altherian.hbox.comm.in.MachineIn;
import org.altherian.hbox.comm.in.ServerIn;
import org.altherian.hbox.constant.MachineAttribute;
import org.altherian.hbox.data.Machine;
import org.altherian.hboxd.comm.io.factory.MachineIoFactory;
import org.altherian.hboxd.core._Hyperbox;
import org.altherian.hboxd.core.action.ASingleTaskAction;
import org.altherian.hboxd.core.model._Machine;
import org.altherian.hboxd.hypervisor.vm._RawVM;
import org.altherian.hboxd.session.SessionContext;
import java.util.Arrays;
import java.util.List;

public final class MachineCreateAction extends ASingleTaskAction {

   @Override
   public List<String> getRegistrations() {
      return Arrays.asList(Command.VBOX.getId() + HypervisorTasks.MachineCreate.getId());
   }

   @Override
   public boolean isQueueable() {
      return true;
   }

   @Override
   public void run(Request request, _Hyperbox hbox) {
      MachineIn mIn = request.get(MachineIn.class);
      String serverId = mIn.getServerId();
      if (request.has(ServerIn.class)) {
         serverId = request.get(ServerIn.class).getId();
      }

      String osTypeId = mIn.hasSetting(MachineAttribute.OsType) ? mIn.getSetting(MachineAttribute.OsType).getString() : null;
      Machine settingTemplate = hbox.getHypervisor().getMachineSettings(osTypeId);
      _RawVM newVm = hbox.getHypervisor().createMachine(mIn.getUuid(), mIn.getName(), osTypeId);
      newVm.applyConfiguration(settingTemplate);

      _Machine vm = hbox.getServer(serverId).getMachine(newVm.getUuid());
      SessionContext.getClient().putAnswer(new Answer(request, AnswerType.DATA, MachineIoFactory.get(vm)));
   }

}
