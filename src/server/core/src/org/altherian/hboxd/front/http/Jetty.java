/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2015 - Kamax.io
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

package org.altherian.hboxd.front.http;

import org.altherian.hboxd.HBoxServer;
import org.altherian.tool.logging.Logger;
import org.eclipse.jetty.annotations.AnnotationParser.AbstractHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Jetty extends AbstractHandler {

   private int httpport;
   private Server _server;


   public Jetty() {
      httpport = Integer.parseInt(HBoxServer.getSetting("http_port", "8070"));
      _server = new Server(httpport);

   }

   public void start() {
      try {
         Logger.info("Start Jetty http server on port: ".concat(String.valueOf(httpport)));

         WebAppContext context = new WebAppContext();
         context.setResourceBase("src/org/altherian/hboxd/front/http/");
         context.setContextPath("/");
         context.setParentLoaderPriority(true);

         _server.setHandler(context);
         _server.start();

         //     Server/src/org/altherian/hboxd/front/http/context/
         _server.join();
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void stop() {
      try {
         _server.stop();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
