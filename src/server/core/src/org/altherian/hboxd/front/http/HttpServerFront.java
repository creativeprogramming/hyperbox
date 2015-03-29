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

import java.util.HashMap;
import java.util.Map;
import org.altherian.hbox.comm.Request;
import org.altherian.hbox.comm._Client;
import org.altherian.hbox.comm.out.event.EventOut;
import org.altherian.hbox.exception.HyperboxException;
import org.altherian.hboxd.front._Front;
import org.altherian.hboxd.front._RequestReceiver;


public class HttpServerFront implements _Front {

   private _RequestReceiver reqRecv;
   private Map<String, _Client> clients = new HashMap<String, _Client>();

   Jetty httpserver = new Jetty();

   @Override
   public void start(_RequestReceiver receiver) throws HyperboxException {
      httpserver.start();
   }


   @Override
   public void stop() {
      httpserver.stop();
   }

   @Override
   public void broadcast(EventOut ev) {
      for (_Client client : clients.values()) {
         client.post(ev);
      }
   }

   private void newConnectionEvent(String clientIp, String browerId) {
      _Client httpClient = new HttpClient(clientIp, browerId);
      reqRecv.register(httpClient);
      clients.put(clientIp, httpClient);
   }

   private void newRequestReceived(String clientIp, String raw) {
      // parse request to extract Request object and all that is included into it
      Request req = RequestBuilder.extract(raw); //whatever can translate the raw text
      reqRecv.postRequest(clients.get(clientIp), req);
   }

   private void disconnectClientEvent(String clientIp) {
      reqRecv.unregister(clients.get(clientIp));
   }

}
