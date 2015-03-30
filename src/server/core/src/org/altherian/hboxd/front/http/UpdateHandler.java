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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.altherian.tool.logging.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class UpdateHandler extends AbstractHandler implements Runnable {

   String _target;
   Request _request1;
   HttpServletRequest _request;
   HttpServletResponse _response;

   public UpdateHandler() {

   }

   public UpdateHandler(String target, Request request1, HttpServletRequest request, HttpServletResponse response) {
      _target = target;
      _request1 = request1;
      _request = request;
      _response = response;
      try {
         Thread.sleep(10000);
         _request1.setHandled(true);
         _response.setContentType("text/html");
         _response.setStatus(HttpServletResponse.SC_OK);
         _response.getWriter().println("<h1>Hello ManyHandler");

      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }

   }

   @Override
   public void handle(String target, Request request1, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Logger.info("Start webclient update thread");
      new Thread(new UpdateHandler(target, request1, request, response)).start();
   }

   @Override
   public void run() {

   }
}
