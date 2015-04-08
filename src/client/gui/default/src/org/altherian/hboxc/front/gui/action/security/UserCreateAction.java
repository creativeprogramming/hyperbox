/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2013 Maxime Dor
 * hyperbox at altherian dot org
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

package org.altherian.hboxc.front.gui.action.security;

import org.altherian.hbox.comm.Command;
import org.altherian.hbox.comm.HyperboxTasks;
import org.altherian.hbox.comm.Request;
import org.altherian.hbox.comm.in.ServerIn;
import org.altherian.hbox.comm.in.UserIn;
import org.altherian.hboxc.front.gui.Gui;
import org.altherian.hboxc.front.gui.builder.IconBuilder;
import org.altherian.hboxc.front.gui.security.user.UserEditor;
import org.altherian.hboxc.front.gui.server._SingleServerSelector;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class UserCreateAction extends AbstractAction {

   private _SingleServerSelector select;

   public UserCreateAction(_SingleServerSelector select) {
      this(select, "Create");
   }

   public UserCreateAction(_SingleServerSelector select, String label) {
      super(label, IconBuilder.getTask(HyperboxTasks.UserCreate));
      this.select = select;
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      UserIn usrIn = UserEditor.getInput();
      if (usrIn != null) {
         Gui.post(new Request(Command.HBOX, HyperboxTasks.UserCreate, new ServerIn(select.getServer()), usrIn));
      }
   }

}
