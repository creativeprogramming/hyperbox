/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2013 Maxime Dor
 * hyperbox at altherian dot org
 * 
 * http://hyperbox.altherian.org
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

package org.altherian.hboxc.front.gui.workers;

import org.altherian.hbox.comm.out.ServerOut;
import org.altherian.hbox.comm.out.TaskOut;
import org.altherian.hboxc.front.gui.Gui;
import org.altherian.hboxc.front.gui.utils.AxSwingWorker;
import org.altherian.hboxc.front.gui.worker.receiver._TaskListReceiver;
import java.util.List;

public class TaskListWorker extends AxSwingWorker<_TaskListReceiver, Void, TaskOut> {
   
   private String srvId;
   
   public TaskListWorker(_TaskListReceiver recv, String srvId) {
      super(recv);
      this.srvId = srvId;
   }
   
   @Override
   protected Void doInBackground() throws Exception {
      for (TaskOut tOut : Gui.getServer(srvId).listTasks()) {
         publish(tOut);
      }
      
      return null;
   }
   
   @Override
   protected void process(List<TaskOut> tOutList) {
      getReceiver().add(tOutList);
   }
   
   public static void execute(_TaskListReceiver recv, ServerOut srvOut) {
      (new TaskListWorker(recv, srvOut.getId())).execute();
   }
   
   public static void execute(_TaskListReceiver recv, String srvId) {
      (new TaskListWorker(recv, srvId)).execute();
   }
   
}