/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2014 Maxime Dor
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

package org.altherian.hboxc.front.gui.action.module;

import org.altherian.hbox.comm.Command;
import org.altherian.hbox.comm.HyperboxTasks;
import org.altherian.hbox.comm.Request;
import org.altherian.hbox.comm.in.ModuleIn;
import org.altherian.hbox.comm.in.ServerIn;
import org.altherian.hbox.comm.out.ModuleOut;
import org.altherian.hboxc.front.gui.Gui;
import org.altherian.hboxc.front.gui.builder.IconBuilder;
import org.altherian.hboxc.front.gui.module._ModuleSelector;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ModuleLoadAction extends AbstractAction {

   private _ModuleSelector selector;

   public ModuleLoadAction(_ModuleSelector selector) {
      super("Load", IconBuilder.getTask(HyperboxTasks.ModuleLoad));
      this.selector = selector;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      for (ModuleOut mod : selector.getModuleSelection()) {
         Gui.post(new Request(Command.HBOX, HyperboxTasks.ModuleLoad, new ServerIn(selector.getServerId()), new ModuleIn(mod.getId())));
      }
   }

}
