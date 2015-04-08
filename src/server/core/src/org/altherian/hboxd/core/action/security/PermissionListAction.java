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

package org.altherian.hboxd.core.action.security;

import org.altherian.hbox.comm.Command;
import org.altherian.hbox.comm.HyperboxTasks;
import org.altherian.hbox.comm.Request;
import org.altherian.hbox.comm.in.UserIn;
import org.altherian.hboxd.comm.io.factory.PermissionIoFactory;
import org.altherian.hboxd.core._Hyperbox;
import org.altherian.hboxd.core.action.ASingleTaskAction;
import org.altherian.hboxd.security.SecurityContext;
import org.altherian.hboxd.security._ActionPermission;
import org.altherian.hboxd.security._ItemPermission;
import org.altherian.hboxd.security._User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionListAction extends ASingleTaskAction {

   @Override
   public List<String> getRegistrations() {
      return Arrays.asList(Command.HBOX.getId() + HyperboxTasks.PermissionList.getId());
   }

   @Override
   public boolean isQueueable() {
      return false;
   }

   @Override
   public void process(Request request, _Hyperbox hbox) {
      List<_ActionPermission> actionPermList = new ArrayList<_ActionPermission>();
      List<_ItemPermission> itemPermList = new ArrayList<_ItemPermission>();
      _User usr = SecurityContext.getUser();

      if (request.has(UserIn.class)) {
         UserIn usrIn = request.get(UserIn.class);
         usr = hbox.getSecurityManager().getUser(usrIn.getId());

         actionPermList.addAll(hbox.getSecurityManager().listActionPermissions(usr));
         itemPermList.addAll(hbox.getSecurityManager().listItemPermissions(usr));
      }

      for (_ActionPermission perm : actionPermList) {
         send(PermissionIoFactory.get(usr, perm));
      }
      for (_ItemPermission perm : itemPermList) {
         send(PermissionIoFactory.get(usr, perm));
      }
   }

}
