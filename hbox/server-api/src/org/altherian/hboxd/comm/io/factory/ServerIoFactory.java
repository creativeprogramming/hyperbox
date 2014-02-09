/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2013 Maxime Dor
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.altherian.hboxd.comm.io.factory;

import org.altherian.hbox.comm.io.BooleanSettingIO;
import org.altherian.hbox.comm.io.SettingIO;
import org.altherian.hbox.comm.io.StringSettingIO;
import org.altherian.hbox.comm.output.ServerOutput;
import org.altherian.hbox.constant.ServerAttributes;
import org.altherian.hboxd.HBoxServer;
import org.altherian.hboxd.server._Server;

import java.util.ArrayList;
import java.util.List;

public class ServerIoFactory {
   
   private ServerIoFactory() {
      // static-only class
   }
   
   public static ServerOutput get(_Server srv) {
      List<SettingIO> settings = new ArrayList<SettingIO>();
      settings.add(new StringSettingIO(ServerAttributes.Name, srv.getName()));
      settings.add(new StringSettingIO(ServerAttributes.Type, srv.getType()));
      settings.add(new StringSettingIO(ServerAttributes.Version, srv.getVersion()));
      settings.add(new BooleanSettingIO(ServerAttributes.IsHypervisorConnected, srv.isConnected()));
      ServerOutput srvOut = new ServerOutput(srv.getId(), settings);
      return srvOut;
   }
   
   public static ServerOutput get() {
      return get(HBoxServer.get());
   }

}